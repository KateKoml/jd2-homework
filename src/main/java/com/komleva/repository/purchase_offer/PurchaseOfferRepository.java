package com.komleva.repository.purchase_offer;

import com.komleva.domain.Product;
import com.komleva.domain.PurchaseOffer;
import com.komleva.repository.CRUDRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseOfferRepository extends CRUDRepository<Long, PurchaseOffer> {
    public void hardDelete(Long id);

    public List<Product> getProductByName(String search);

    public void updateOfferPrice(Long offerId, BigDecimal newPrice);
}
