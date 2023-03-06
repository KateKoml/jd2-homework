package com.komleva.repository;

import com.komleva.domain.User;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {

    public List<User> findAllFemales();

    public List<User> findAllMales();

    public String getNameByPhone(String phoneNumber);
}
