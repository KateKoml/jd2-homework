package com.komleva.service;

import com.komleva.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findOne(Long id);

    List<User> findAll();

     User findById(Long id);

    User create(User object); //проверка на валидность e-mail, phone, ip, password с 1 заглавной и одной цифрой

    User update(User object);

    Optional<User> delete(Long id);
}
