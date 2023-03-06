package com.komleva.repository;

import com.komleva.configuration.DatabaseProperties;
import com.komleva.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Primary
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    /*public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/trading_platform";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "postgres";*/

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String GENDER = "gender";
    private static final String E_MAIL = "e_mail";
    private static final String PHONE = "phone";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_IP = "user_ip";
    private static final String HASH = "hash";
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
            user.setUserIp(rs.getString(USER_IP));
            user.setHash(rs.getString(HASH));
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
    public User findOne(Long id) {
        /*List<User> users = findAll();
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);*/
        final String findOneQuery = "select * from users where id = ?";
        registerDriver();
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                list.add(parseResultSet(rs));
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public User create(User user) {
        final String createQuery = "insert into users (name, surname, gender, e_mail, phone, login, password, user_ip, hash, created, changed)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setString(8, user.getUserIp());
            preparedStatement.setString(9, user.getHash());
            preparedStatement.setTimestamp(10, user.getCreated());
            preparedStatement.setTimestamp(11, user.getChanged());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return user;
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
    public void delete(Long id) {
        User thisUser = findOne(id);

        long millisecondsBetweenTwoDates = thisUser.getChanged().getTime() - thisUser.getCreated().getTime();
        int daysBetweenDates = (int) (millisecondsBetweenTwoDates / (24 * 60 * 60 * 1000));
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
    public List<User> findAllFemales() {
        final String findAllFemalesQuery = "select * from users where gender = 'F'";

        List<User> women = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllFemalesQuery)
        ) {
            while (rs.next()) {
                women.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return women;
        /*List<User> users = findAll();
        return users.stream().filter(user -> user.getGender().equals("F")).collect(Collectors.toList());*/
    }

    @Override
    public List<User> findAllMales() {
        final String findAllMalesQuery = "select * from users where gender = 'M'";

        List<User> men = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllMalesQuery)
        ) {
            while (rs.next()) {
                men.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return men;
        /*List<User> users = findAll();
        return users.stream().filter(user -> user.getGender().equals("M")).collect(Collectors.toList());*/
    }

    @Override
    public String getNameByPhone(String phoneNumber) {
        final String getNameByPhoneQuery = "select * from users where phone = ?";

        List<User> result = new ArrayList<>();
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getNameByPhoneQuery)) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        User user = result.stream().findAny().orElse(null);
        return Objects.requireNonNull(user).getName() + " " + user.getSurname();
        /*List<User> users = findAll();
        User result = users.stream().filter(user -> Objects.equals(user.getPhone(), phoneNumber)).findAny().orElse(null);
        return Objects.requireNonNull(result).getName() + " " + result.getSurname();*/
    }
}
