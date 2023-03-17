package com.komleva;

import com.komleva.configuration.DatabaseProperties;
import com.komleva.domain.User;
import com.komleva.repository.user.UserRepository;
import com.komleva.repository.user.UserRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl(new DatabaseProperties());

        System.out.println("\n Method findAll");
        List<User> all = userRepository.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}