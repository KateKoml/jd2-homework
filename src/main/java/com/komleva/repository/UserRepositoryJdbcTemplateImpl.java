package com.komleva.repository;

import com.komleva.domain.User;
import com.komleva.exceptions.EntityNotFoundException;
import com.komleva.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class UserRepositoryJdbcTemplateImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper));
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users order by id desc", userRowMapper);
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
    public Optional<User> delete(Long id) {
        return null;
    }

    @Override
    public List<User> findAllUsersByGender(String gender) {
        return jdbcTemplate.query("select * from users where gender = ?", new Object[]{gender}, userRowMapper);
    }

    @Override
    public String getFullNameByPhone(String phoneNumber) {
        List<User> users = jdbcTemplate.query("select * from users where phone = ?", new Object[]{phoneNumber}, userRowMapper);
        String fullName = null;
        try {
            for (User user : users) {
                fullName = user.getName() + " " + user.getSurname();
            }
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("There is no user with this phone number");

        }
        return fullName;
    }
}
