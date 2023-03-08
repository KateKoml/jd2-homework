package com.komleva.repository;

import com.komleva.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Primary
public class UserSecondRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findOne(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
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
    public Optional<User> delete(Long id) {
        return null;
    }

    @Override
    public List<User> findAllUsersByGender(String gender) {
        return null;
    }

    @Override
    public String getFullNameByPhone(String phoneNumber) {
        return null;
    }
}
