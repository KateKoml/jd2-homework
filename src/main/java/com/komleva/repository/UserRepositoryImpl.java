package com.komleva.repository;

import com.komleva.configuration.DatabaseProperties;
import com.komleva.domain.User;
import com.komleva.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String GENDER = "gender";
    private static final String E_MAIL = "e_mail";
    private static final String PHONE = "phone";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_IP = "user_ip";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String IS_DELETED = "is_deleted";
    private final DatabaseProperties properties;

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from users order by id desc";

        List<User> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {
            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return result;
    }

    private User parseResultSet(ResultSet rs) {
        User user;

        try {
            user = new User();
            user.setId(rs.getLong(ID)); //1 or id
            user.setName(rs.getString(NAME));
            user.setSurname(rs.getString(SURNAME));
            user.setGender(rs.getString(GENDER));
            user.setEmail(rs.getString(E_MAIL));
            user.setPhone(rs.getString(PHONE));
            user.setLogin(rs.getString(LOGIN));
            user.setPassword(rs.getString(PASSWORD));
            user.setUserIP(rs.getString(USER_IP));
            user.setCreated(rs.getTimestamp(CREATED));
            user.setChanged(rs.getTimestamp(CHANGED));
            user.setIsDeleted(rs.getBoolean(IS_DELETED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private void registerDriver() {
        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getName());
        try {
            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        final String findOneQuery = "select * from users where id = ?";
        registerDriver();
        Optional<User> user = Optional.empty();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = Optional.ofNullable(parseResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        final String findOneQuery = "select * from users where id = ?";
        registerDriver();
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        User finedUser = new User();
        try {
            finedUser = list.get(0);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("There is no such user id");
        }
        return finedUser;
    }

    @Override
    public User create(User user) {
        final String createQuery = "insert into users (name, surname, gender, e_mail, phone, login, password, user_ip, created, changed)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final String findLastIdQuery = "select id from users order by id desc limit 1";
        Long lastId = 0L;
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getUserIP());
            preparedStatement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

            PreparedStatement ps = connection.prepareStatement(findLastIdQuery);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lastId = rs.getLong("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return findById(lastId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update users set name = ?, surname = ?, gender = ?, e_mail = ?, phone = ?, " +
                "password = ?, changed = ?, is_deleted = ? where id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement statements = connection.prepareStatement(updateQuery)) {
            statements.setString(1, user.getName());
            statements.setString(2, user.getSurname());
            statements.setString(3, user.getGender());
            statements.setString(4, user.getEmail());
            statements.setString(5, user.getPhone());
            statements.setString(6, user.getPassword());
            statements.setTimestamp(7, user.getChanged());
            statements.setBoolean(8, user.getIsDeleted());
            statements.setLong(9, user.getId());
            statements.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return user;
    }

    @Override
    public Optional<User> delete(Long id) {
        final int HOURS_IN_DAY = 24;
        final int MIN_IN_HOUR = 60;
        final int SEC_IN_MIN = 60;
        final int MILLISEC_IN_SEC = 1000;
        Optional<User> thisUser = findOne(id);

        long millisecondsBetweenTwoDates = thisUser.get().getChanged().getTime() - thisUser.get().getCreated().getTime();
        int daysBetweenDates = (int) (millisecondsBetweenTwoDates / (HOURS_IN_DAY * MIN_IN_HOUR * SEC_IN_MIN * MILLISEC_IN_SEC));
        if (daysBetweenDates >= 30) {
            hardDelete(id);
        } else {
            final String deleteQuery = "update users set changed = ?, is_deleted = true where id = ?";
            registerDriver();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException("SQL Issues!");
            }
        }
        Optional<User> checkedUser = findOne(id);
        return checkedUser;
    }

    public void hardDelete(Long id) {
        final String hardDeleteQuery = "delete from users where id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(hardDeleteQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<User> findAllUsersByGender(String gender) {
        final String findAllUsersByGenderQuery = "select * from users where gender = ?";

        List<User> users = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllUsersByGenderQuery)) {
            preparedStatement.setString(1, gender);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return users;
    }

    @Override
    public String getFullNameByPhone(String phoneNumber) {
        final String getNameByPhoneQuery = "select * from users where phone = ?";
        Optional<User> user = Optional.empty();
        String result = "";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getNameByPhoneQuery)) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = Optional.ofNullable(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        try {
            result = user.get().getName() + " " + user.get().getSurname();
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("There is no user with this phone number");
        }
        return result;
    }
}
