package com.komleva.service;

import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.repository.UserRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAggServiceImpl implements UserAggregationService {

    private final UserRepository userRepository = new UserRepositoryImpl();
//  private final UserService userService = new UserServiceImpl();

    @Override
    public Map<String, String> getUsersAndPhones() {

        List<User> users = userRepository.findAll();
        Map<String, String> resultMap = new HashMap<>();
        for (User user : users) {
            String phone = user.getPhone();
            String userName = userRepository.getNameByPhone(phone);
            resultMap.put(userName, phone);
        }
        return resultMap;
    }
}
