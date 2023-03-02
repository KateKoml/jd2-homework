package com.komleva.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class PurchaseOffer {
    private Long id;
    private Long sellerId;
    private Long customerId;
    private Integer statusId;
    private String productName;
    private Integer productCategoryId;
    private Integer productConditionId;
    private Double price;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public PurchaseOffer(Long id, Long sellerId, Long customerId, Integer statusId, String productName, Integer productCategoryId, Integer productConditionId, Double price, Timestamp created, Timestamp changed, Boolean isDeleted) {
        this.id = id;
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.statusId = statusId;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.productConditionId = productConditionId;
        this.price = price;
        this.created = created;
        this.changed = changed;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductConditionId() {
        return productConditionId;
    }

    public void setProductConditionId(Integer productConditionId) {
        this.productConditionId = productConditionId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseOffer that = (PurchaseOffer) o;

        if (!id.equals(that.id)) return false;
        if (!sellerId.equals(that.sellerId)) return false;
        if (!Objects.equals(customerId, that.customerId)) return false;
        if (!statusId.equals(that.statusId)) return false;
        if (!productName.equals(that.productName)) return false;
        if (!productCategoryId.equals(that.productCategoryId)) return false;
        if (!productConditionId.equals(that.productConditionId)) return false;
        if (!price.equals(that.price)) return false;
        if (!created.equals(that.created)) return false;
        if (!changed.equals(that.changed)) return false;
        return isDeleted.equals(that.isDeleted);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + sellerId.hashCode();
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + statusId.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + productCategoryId.hashCode();
        result = 31 * result + productConditionId.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + changed.hashCode();
        result = 31 * result + isDeleted.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PurchaseOffer{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", customerId=" + customerId +
                ", statusId=" + statusId +
                ", productName='" + productName + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", productConditionId=" + productConditionId +
                ", price=" + price +
                ", created=" + created +
                ", changed=" + changed +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
