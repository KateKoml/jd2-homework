package com.komleva;

import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.repository.UserRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();

        List<User> all = userRepository.findAll();

        for (User user : all) {
            System.out.println(user);
        }
    }
}
