package com.komleva.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class PurchaseOfferCreateRequest {
    private Long id;
    private Long sellerId;
    private Long customerId;
    private Integer statusId;
    private String productName;
    private Integer productCategoryId;
    private Integer productConditionId;
    private BigDecimal price;
    private Boolean isDeleted;
}
