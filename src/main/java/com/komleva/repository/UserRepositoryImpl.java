package com.komleva.repository;

import com.komleva.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
//bean id=userRepositoryImpl   class=UserRepositoryImpl
//@Component
public class UserRepositoryImpl implements UserRepository {

    public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/trading_platform";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "postgres";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String GENDER = "gender";
    private static final String ADDRESS_ID = "address_id";
    private static final String E_MAIL = "e_mail";
    private static final String PHONE = "phone";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_IP = "user_ip";
    private static final String HASH = "hash";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String IS_DELETED = "is_deleted";


    @Override
    public List<User> findAll() {

        /*
         * 1) Driver Manager - getting connection from DB
         * */

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
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private User parseResultSet(ResultSet rs) {

        User user;

        try {
            user = new User();
            user.setId(rs.getLong(ID)); //1 or id
            user.setName(rs.getString(NAME));
            user.setSurname(rs.getString(SURNAME));
            user.setGender(rs.getString(GENDER));
            user.setAddressId(rs.getLong(ADDRESS_ID));
            user.setEMail(rs.getString(E_MAIL));
            user.setPhone(rs.getString(PHONE));
            user.setLogin(rs.getString(LOGIN));
            user.setPassword(rs.getString(PASSWORD));
            user.setUserIp(rs.getString(USER_IP));
            user.setHash(rs.getString(HASH));
            user.setCreated(rs.getTimestamp(CREATED));
            user.setChanged(rs.getTimestamp(CHANGED));
            user.setDeleted(rs.getBoolean(IS_DELETED));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private void registerDriver() {
        try {
            Class.forName(POSTRGES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);
        try {
            return DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void searchUser() {

    }
}
