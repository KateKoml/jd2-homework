package com.komleva.service;

import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.komleva.domain.User;
import com.komleva.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private Pattern pattern;
    private Matcher matcher;

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
    public User create(User user) {
        if (!nameValidation(user.getName()) || !nameValidation(user.getSurname())) {
            throw new RuntimeException("Try another name, without numbers");
        } else if (!emailValidation(user.getEmail())) {
            throw new RuntimeException("This e-mail doesn't exist!");
        } else if (!phoneValidation(user.getPhone())) {
            throw new RuntimeException("This phone doesn't exist!");
        } else if (!passwordValidation(user.getPassword())) {
            throw new RuntimeException("The password must be between 6 and 20 characters and contains numbers, lowercase and uppercase letters.");
        } else if (!ipValidation((user.getUserIP()))) {
            throw new RuntimeException("Wrong IP!");
        }
        return userRepository.create(user);
    }

    public boolean nameValidation(String name) {
        final String NAME_PATTERN = "^[A-Z][a-z]*$";
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean emailValidation(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean phoneValidation(String phone) {
        final String PHONE_PATTERN = "^(375|80)(29|25|33|44)(\\d{3})(\\d{2})(\\d{2})$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean passwordValidation(String password) {
        final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean ipValidation(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress instanceof InetAddress || inetAddress instanceof Inet6Address;
        } catch (UnknownHostException e) {
            return false;
        }
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
