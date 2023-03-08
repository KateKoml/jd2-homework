package com.komleva.service;

import com.komleva.configuration.DatabaseProperties;
import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAggServiceImpl implements UserAggregationService {
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Map<String, String>> getUsersAndPhones() {
        final String findAllQuery = "select name, surname, phone from users";

        Map<String, Map<String, String>> result = new HashMap<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {
            while (rs.next()) {
                Map<String, String> fullName = new HashMap<>();
                fullName.put(rs.getString(SURNAME), rs.getString(NAME));
                result.put(rs.getString(PHONE), fullName);

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
}
