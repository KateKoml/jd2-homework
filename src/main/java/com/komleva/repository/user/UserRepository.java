package com.komleva.repository.user;

import com.komleva.domain.User;
import com.komleva.repository.CRUDRepository;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {
    public void hardDelete(Long id);

    public List<User> findAllUsersByGender(String gender);

    public String getFullNameByPhone(String phoneNumber);
}
