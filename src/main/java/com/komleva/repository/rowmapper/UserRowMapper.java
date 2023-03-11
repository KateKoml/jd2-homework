package com.komleva.repository.rowmapper;

import com.komleva.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.komleva.enums.UserColumns.ID;
import static com.komleva.enums.UserColumns.NAME;
import static com.komleva.enums.UserColumns.SURNAME;
import static com.komleva.enums.UserColumns.GENDER;
import static com.komleva.enums.UserColumns.E_MAIL;
import static com.komleva.enums.UserColumns.PHONE;
import static com.komleva.enums.UserColumns.LOGIN;
import static com.komleva.enums.UserColumns.PASSWORD;
import static com.komleva.enums.UserColumns.USER_IP;
import static com.komleva.enums.UserColumns.CREATED;
import static com.komleva.enums.UserColumns.CHANGED;
import static com.komleva.enums.UserColumns.IS_DELETED;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user;

        try {

            user = User.builder()
                    .id(rs.getLong(ID.name()))
                    .name(rs.getString(NAME.name()))
                    .surname(rs.getString(SURNAME.name()))
                    .gender(rs.getString(GENDER.name()))
                    .email(rs.getString(E_MAIL.name()))
                    .phone(rs.getString(PHONE.name()))
                    .login(rs.getString(LOGIN.name()))
                    .password(rs.getString(PASSWORD.name()))
                    .userIP(rs.getString(USER_IP.name()))
                    .created(rs.getTimestamp(CREATED.name()))
                    .changed(rs.getTimestamp(CHANGED.name()))
                    .isDeleted(rs.getBoolean(IS_DELETED.name()))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
