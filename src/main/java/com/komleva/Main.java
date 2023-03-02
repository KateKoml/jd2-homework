package com.komleva;

import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.repository.UserRepositoryImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User("Gloria", "Fuxy", "F", "worldglory@gmail.com",
                "375295782332", "gloryGirl", "dskwlf84", "181.120.15.96",
                "jfdl4598349834ntfk5", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(userRepository.create(newUser));*/

        /*UserRepository userRepository = new UserRepositoryImpl();
        userRepository.delete(4L);*/

        /*UserRepository userRepository = new UserRepositoryImpl();
        User newUser = new User(4L, "Max", "Karpov", "M", "max999killer@gmail.com",
                "375444798503", "maxkiller", "8juhkli7", "168.87.24.120",
                "7gjlio7fcjlsgdfjr857", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), false);
        System.out.println(userRepository.update(newUser));*/

        UserRepository userRepository = new UserRepositoryImpl();
        System.out.println(userRepository.findOne(1L));
    }
}