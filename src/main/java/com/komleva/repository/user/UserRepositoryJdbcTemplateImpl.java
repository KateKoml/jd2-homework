package com.komleva.repository.user;

import com.komleva.domain.User;
import com.komleva.exceptions.EntityNotFoundException;
import com.komleva.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class UserRepositoryJdbcTemplateImpl implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserRepositoryJdbcTemplateImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public Optional<User> findOne(Long id) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper));
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            throw new EntityNotFoundException("No such id was found");
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            throw new EntityNotFoundException("No such id was found");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users order by id desc", userRowMapper);
    }

    @Override
    public User create(User user) {
        final String createQuery = "insert into users (name, surname, gender, e_mail, phone, login, password, user_ip, created, changed)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(createQuery, new String[]{"id"});
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getLogin());
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getUserIP());
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
            return stmt;
        }, keyHolder);
        Long newId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(newId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update users set name = ?, surname = ?, gender = ?, e_mail = ?, phone = ?, " +
                "password = ?, changed = ?, is_deleted = ? where id = ?";
        jdbcTemplate.update(updateQuery,
                user.getName(),
                user.getSurname(),
                user.getGender(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                Timestamp.valueOf(LocalDateTime.now()),
                user.getIsDeleted(),
                user.getId());
        return findById(user.getId());
    }

    @Override
    public Optional<User> delete(Long id) {
        final int HOURS_IN_DAY = 24;
        final int MIN_IN_HOUR = 60;
        final int SEC_IN_MIN = 60;
        final int MILLISEC_IN_SEC = 1000;
        final int EXPIRATION_DATE = 30;

        final String deleteQuery = "update users set changed = ?, is_deleted = true where id = ?";
        User thisUser = findById(id);
        long millisecondsBetweenTwoDates = thisUser.getChanged().getTime() - thisUser.getCreated().getTime();
        int daysBetweenDates = (int) (millisecondsBetweenTwoDates / (HOURS_IN_DAY * MIN_IN_HOUR * SEC_IN_MIN * MILLISEC_IN_SEC));
        if (daysBetweenDates >= EXPIRATION_DATE) {
            hardDelete(id);
        } else {
            jdbcTemplate.update(deleteQuery,
                    Timestamp.valueOf(LocalDateTime.now()),
                    id);
        }
        return findOne(id);
    }

    public void hardDelete(Long id) {
        final String hardDeleteQuery = "delete from users where id = ?";
        jdbcTemplate.update(hardDeleteQuery, id);
    }

    @Override
    public List<User> findAllUsersByGender(String gender) {
        return jdbcTemplate.query("select * from users where gender = ?", new Object[]{gender}, userRowMapper);
    }

    @Override
    public String getFullNameByPhone(String phoneNumber) {
        String fullName = "Wrong number";
        List<User> users = jdbcTemplate.query("select * from users where phone = ?", new Object[]{phoneNumber}, userRowMapper);
        if (users.size() == 1) {
            for (User user : users) {
                fullName = user.getName() + " " + user.getSurname();
            }
        } else {
            throw new EntityNotFoundException("There is no user with this phone number");
        }
        return fullName;
    }
}
