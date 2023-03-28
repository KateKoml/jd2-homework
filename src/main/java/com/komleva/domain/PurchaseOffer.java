package com.komleva.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PurchaseOffer {
    private Long id;
    private Long sellerId;
    private Long customerId;
    private Integer statusId;
    private String productName;
    private Integer productCategoryId;
    private Integer productConditionId;
    private BigDecimal price;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public PurchaseOffer(Long sellerId, Long customerId, Integer statusId, String productName,
                         Integer productCategoryId, Integer productConditionId, BigDecimal price) {
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.statusId = statusId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productConditionId = productConditionId;
        this.price = price;
    }

    public PurchaseOffer(Long id, Long sellerId, Long customerId, Integer statusId, String productName,
                         Integer productCategoryId, Integer productConditionId, BigDecimal price, Boolean isDeleted) {
        this.id = id;
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.statusId = statusId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productConditionId = productConditionId;
        this.price = price;
        this.isDeleted = isDeleted;
    }

    public PurchaseOffer(Long id, Long sellerId, Integer statusId, String productName,
                         Integer productCategoryId, Integer productConditionId, BigDecimal price, Boolean isDeleted) {
        this.id = id;
        this.sellerId = sellerId;
        this.statusId = statusId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productConditionId = productConditionId;
        this.price = price;
        this.isDeleted = isDeleted;
    }

    public PurchaseOffer(Long sellerId, Integer statusId, String productName,
                         Integer productCategoryId, Integer productConditionId, BigDecimal price) {
        this.sellerId = sellerId;
        this.statusId = statusId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productConditionId = productConditionId;
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
