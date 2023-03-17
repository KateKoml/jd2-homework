package com.komleva.repository.purchase_offer;

import com.komleva.domain.PurchaseOffer;

import java.util.List;
import java.util.Optional;

public class PurchaseOfferJdbcTemplateImpl implements PurchaseOfferRepository {
    @Override
    public Optional<PurchaseOffer> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public PurchaseOffer findById(Long id) {
        return null;
    }

    @Override
    public List<PurchaseOffer> findAll() {
        return null;
    }

    @Override
    public PurchaseOffer create(PurchaseOffer object) {
        return null;
    }

    @Override
    public PurchaseOffer update(PurchaseOffer object) {
        return null;
    }

    @Override
    public Optional<PurchaseOffer> delete(Long id) {
        return Optional.empty();
    }
}
