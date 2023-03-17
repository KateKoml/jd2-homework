package com.komleva.service;

import com.komleva.configuration.DatabaseProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static com.komleva.enums.UserColumns.NAME;
import static com.komleva.enums.UserColumns.PHONE;
import static com.komleva.enums.UserColumns.SURNAME;

@Service
@RequiredArgsConstructor
public class UserAggServiceImpl implements UserAggregationService {
    private final DatabaseProperties properties;

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
                fullName.put(rs.getString(SURNAME.name()), rs.getString(NAME.name()));
                result.put(rs.getString(PHONE.name()), fullName);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return result;
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
