package com.komleva.service.purchase_offer;

import com.komleva.domain.User;

import java.util.List;

public interface PurchaseOfferService {
    User findOne(Long id);

    List<User> findAll();

    User create(User object);

    User update(User object);

    void delete(Long id);
}
