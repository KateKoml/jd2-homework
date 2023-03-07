package com.komleva.service;

import com.komleva.configuration.DatabaseProperties;
import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAggServiceImpl implements UserAggregationService {

    @Autowired
    private UserRepository userRepository;

    public UserAggServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, String> getUsersAndPhones() {

        List<User> users = userRepository.findAll(); //через sql
        Map<String, String> resultMap = new HashMap<>();
        for (User user : users) {
            String phone = user.getPhone();
            String userName = userRepository.getFullNameByPhone(phone);
            resultMap.put(userName, phone);
        }
        return resultMap;
    }
}
