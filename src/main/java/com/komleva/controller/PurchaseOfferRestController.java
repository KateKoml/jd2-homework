package com.komleva.controller;

import com.komleva.domain.Product;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/purchase-offers")
@RequiredArgsConstructor
public class PurchaseOfferRestController {
    private final PurchaseOfferRepository purchaseOfferRepository;

    @GetMapping()
    public ResponseEntity<List<PurchaseOffer>> getAllPurchaseOffers() {
        List<PurchaseOffer> purchaseOffers = purchaseOfferRepository.findAll();
        return new ResponseEntity<>(purchaseOffers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOffer> getOnePurchaseOffer(@PathVariable Long id) {
        PurchaseOffer purchaseOffer = purchaseOfferRepository.findById(id);
        return new ResponseEntity<>(purchaseOffer, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PurchaseOffer> createPurchaseOffer(@RequestBody PurchaseOffer purchaseOffer) {
        return new ResponseEntity<>(purchaseOfferRepository.create(purchaseOffer), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<PurchaseOffer> updatePurchaseOffer(@RequestBody PurchaseOffer purchaseOffer) {
        return new ResponseEntity<>(purchaseOfferRepository.update(purchaseOffer), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Optional<PurchaseOffer>> deletePurchaseOffer(@RequestBody PurchaseOffer purchaseOffer) {
        return new ResponseEntity<>(purchaseOfferRepository.delete(purchaseOffer.getId()), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductByName(@RequestParam(value = "query") String query) {
        List<Product> products = purchaseOfferRepository.getProductByName(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<PurchaseOffer> updatePriceOfOffer(@PathVariable Long id,
                                                     @RequestParam BigDecimal newPrice) {
        purchaseOfferRepository.updateOfferPrice(id, newPrice);
        return new ResponseEntity<>(purchaseOfferRepository.findById(id), HttpStatus.OK);
    }
}
