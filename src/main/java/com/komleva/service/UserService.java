package com.komleva.service;

import com.komleva.domain.User;

import java.util.List;

public interface UserService {
        User findOne(Long id);

        List<User> findAll();

        User create(User object);

        User update(User object);

        void delete(Long id);
}
