package com.komleva.repository;

import com.komleva.domain.User;

import java.util.List;
import java.util.Optional;

//K - key, datatype of PK
//T - type of object
public interface CRUDRepository<K, T> {
    Optional<T> findOne(K id);

    T findById(K id); //EntityNotFoundException("User with id" + id + " ");

    List<T> findAll();

    T create(T object);

    T update(T object);

    Optional<T> delete(K id);
}
