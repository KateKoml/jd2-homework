package com.komleva.repository;

import java.util.List;

//K - key, datatype of PK
//T - type of object
public interface CRUDRepository<K, T> {
    T findOne(K id);

    List<T> findAll();

    T create(T object);

    T update(T object);

    void delete(K id);
}
