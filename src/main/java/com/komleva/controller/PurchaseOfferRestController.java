package com.komleva.controller;

import com.komleva.domain.PurchaseOffer;
import com.komleva.repository.purchase_offer.PurchaseOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/purchase-offers")
@RequiredArgsConstructor
public class PurchaseOfferRestController {
    private final PurchaseOfferRepository purchaseOfferRepository;

    @GetMapping()
    public ResponseEntity<Object> getAllPurchaseOffers() {
        List<PurchaseOffer> purchaseOffers = purchaseOfferRepository.findAll();
        return new ResponseEntity<>(purchaseOffers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePurchaseOffer(@PathVariable Long id) {
        PurchaseOffer purchaseOffer = purchaseOfferRepository.findById(id);
        return new ResponseEntity<>(purchaseOffer, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createPurchaseOffer(@RequestBody PurchaseOffer purchaseOffer) {
        return new ResponseEntity<>(purchaseOfferRepository.create(purchaseOffer), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Object> updatePurchaseOffer(@RequestBody PurchaseOffer purchaseOffer) {
        return new ResponseEntity<>(purchaseOfferRepository.update(purchaseOffer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePurchaseOffer(@PathVariable Long id) {
        return new ResponseEntity<>(purchaseOfferRepository.delete(id), HttpStatus.OK);
    }
}
