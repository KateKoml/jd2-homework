package com.komleva;

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
        UserRepository userRepository = new UserRepositoryImpl();
        UserAggregationService userAggregationService = new UserAggServiceImpl(userRepository);

        /*
        System.out.println("\n Method create");
        User newUser = new User("Gloria", "Fuxy", "F", "worldglory@gmail.com",
                "375295782332", "gloryGirl", "dskwlf84", "181.120.15.96",
                "jfdl4598349834ntfk5", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(userRepository.create(newUser));*/

        /*
        System.out.println("\n Method findAll");
        List<User> all = userRepository.findAll();
        for (User user : all) {
            System.out.println(user);
        }*/

        /*
        System.out.println("\n Method delete");
        userRepository.delete(4L);*/

        /*
        System.out.println("\n Method update");
        User newUser = new User(4L, "Max", "Karpov", "M", "max999killer@gmail.com",
                "375444798503", "maxkiller", "8juhkli7", "168.87.24.120",
                "7gjlio7fcjlsgdfjr857", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), false);
        System.out.println(userRepository.update(newUser));*/

        /*
        System.out.println("\n Method findOne");
        System.out.println(userRepository.findOne(1L));*/

        /*
        System.out.println("\n Methods findAllFemales, findAllMales, getNameByPhone");
        System.out.println(userRepository.findAllFemales());
        System.out.println(userRepository.findAllMales());
        System.out.println(userRepository.getNameByPhone("375295905041"));*/

        System.out.println("\n Method getUsersAndPhones");
        System.out.println(userAggregationService.getUsersAndPhones());
    }
}