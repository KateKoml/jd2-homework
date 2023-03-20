package com.komleva.repository.purchase_offer;

import com.komleva.domain.PurchaseOffer;
import com.komleva.repository.CRUDRepository;

import java.math.BigDecimal;

public interface PurchaseOfferRepository extends CRUDRepository<Long, PurchaseOffer> {
    public void hardDelete(Long id);

    public void getProductByName(String search);

    public void updateOfferPrice(Long offerId, BigDecimal newPrice);
}
