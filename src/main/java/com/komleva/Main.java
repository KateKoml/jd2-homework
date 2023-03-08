package com.komleva;

import com.komleva.configuration.DatabaseProperties;
import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.repository.UserRepositoryImpl;
import com.komleva.service.UserAggServiceImpl;
import com.komleva.service.UserAggregationService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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