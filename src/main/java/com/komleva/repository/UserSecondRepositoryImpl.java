package com.komleva.repository;

import com.komleva.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Primary
public class UserSecondRepositoryImpl implements UserRepository {

    @Override
    public User findOne(Long id) {
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
    public void delete(Long id) {

    }

    @Override
    public void searchUser() {

    }

    @Override
    public List<User> findAllFemales() {
        return null;
    }

    @Override
    public List<User> findAllMales() {
        return null;
    }

    @Override
    public String getNameByPhone(String phoneNumber) {
        return null;
    }
}
