package com.komleva.repository;

import com.komleva.domain.User;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {

    public List<User> findAllUsersByGender(String gender);

    public String getFullNameByPhone(String phoneNumber);
}
