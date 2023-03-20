package com.komleva.service;

import com.komleva.domain.User;

import java.util.List;

public interface UserService {
    User findOne(Long id);

    List<User> findAll();

    User create(User object); //проверка на валидность e-mail, phone, ip, password с 1 заглавной и одной цифрой

    User update(User object);

    void delete(Long id);
}
