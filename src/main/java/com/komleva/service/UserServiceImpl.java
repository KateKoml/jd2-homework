package com.komleva.service;

import com.komleva.domain.User;
import com.komleva.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        /*Validation layer*/
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(User object) {
        /*Validation layer*/
//        if (!object.getPhone().startsWith("375")) {
//            throw new RuntimeException("Wrong phone!");
//        }

        return userRepository.create(object);
    }

    @Override
    public User update(User object) {
        return userRepository.update(object);
    }

    @Override
    public Optional<User> delete(Long id) {
        return userRepository.delete(id);
    }
}
