package com.komleva.repository.rowmapper;

import com.komleva.domain.PurchaseOffer;
import com.komleva.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.komleva.enums.PurchaseOfferColumns.CHANGED;
import static com.komleva.enums.PurchaseOfferColumns.CREATED;
import static com.komleva.enums.PurchaseOfferColumns.CUSTOMER_ID;
import static com.komleva.enums.PurchaseOfferColumns.ID;
import static com.komleva.enums.PurchaseOfferColumns.IS_DELETED;
import static com.komleva.enums.PurchaseOfferColumns.PRICE;
import static com.komleva.enums.PurchaseOfferColumns.PRODUCT_CATEGORY_ID;
import static com.komleva.enums.PurchaseOfferColumns.PRODUCT_CONDITION_ID;
import static com.komleva.enums.PurchaseOfferColumns.PRODUCT_NAME;
import static com.komleva.enums.PurchaseOfferColumns.SELLER_ID;
import static com.komleva.enums.PurchaseOfferColumns.STATUS_ID;


public class PurchaseOfferRowMapper implements RowMapper<PurchaseOffer> {
    @Override
    public PurchaseOffer mapRow(ResultSet rs, int rowNum) throws SQLException {
        PurchaseOffer purchaseOffer;
        try {
            purchaseOffer = PurchaseOffer.builder()
                    .id(rs.getLong(ID.name()))
                    .sellerId(rs.getLong(SELLER_ID.name()))
                    .customerId(rs.getLong(CUSTOMER_ID.name()))
                    .statusId(rs.getInt(STATUS_ID.name()))
                    .productName(rs.getString(PRODUCT_NAME.name()))
                    .productCategoryId(rs.getInt(PRODUCT_CATEGORY_ID.name()))
                    .productConditionId(rs.getInt(PRODUCT_CONDITION_ID.name()))
                    .price(rs.getDouble(PRICE.name()))
                    .created(rs.getTimestamp(CREATED.name()))
                    .changed(rs.getTimestamp(CHANGED.name()))
                    .isDeleted(rs.getBoolean(IS_DELETED.name()))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return purchaseOffer;
    }
}
