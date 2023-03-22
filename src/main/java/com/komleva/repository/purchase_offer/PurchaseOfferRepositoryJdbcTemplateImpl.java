package com.komleva.repository.purchase_offer;

import com.komleva.domain.Product;
import com.komleva.domain.PurchaseOffer;
import com.komleva.exceptions.EntityNotFoundException;
import com.komleva.repository.rowmapper.PurchaseOfferRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class PurchaseOfferRepositoryJdbcTemplateImpl implements PurchaseOfferRepository {
    private static final Logger logger = Logger.getLogger(PurchaseOfferRepositoryJdbcTemplateImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PurchaseOfferRowMapper purchaseOfferRowMapper;
    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public Optional<PurchaseOffer> findOne(Long id) {
        Optional<PurchaseOffer> purchaseOffer;
        try {
            purchaseOffer = Optional.ofNullable(jdbcTemplate.queryForObject("select * from purchase_offers where id = "
                    + id, purchaseOfferRowMapper));
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            throw new EntityNotFoundException("No such id was found");
        }
        return purchaseOffer;
    }

    @Override
    public PurchaseOffer findById(Long id) {
        PurchaseOffer purchaseOffer;
        try {
            purchaseOffer = jdbcTemplate.queryForObject("select * from purchase_offers where id = " + id, purchaseOfferRowMapper);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            throw new EntityNotFoundException("No such id was found");
        }
        return purchaseOffer;
    }

    @Override
    public List<PurchaseOffer> findAll() {
        return jdbcTemplate.query("select * from purchase_offers order by id desc", purchaseOfferRowMapper);
    }

    @Override
    public PurchaseOffer create(PurchaseOffer purchaseOffer) {
        final String createQuery = "insert into purchase_offers (seller_id, customer_id, status_id, product_name, " +
                "product_category_id, product_condition_id, price, created, changed)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(createQuery, new String[]{"id"});
            stmt.setLong(1, purchaseOffer.getSellerId());
            stmt.setLong(2, purchaseOffer.getCustomerId());
            stmt.setInt(3, purchaseOffer.getStatusId());
            stmt.setString(4, purchaseOffer.getProductName());
            stmt.setInt(5, purchaseOffer.getProductCategoryId());
            stmt.setInt(6, purchaseOffer.getProductConditionId());
            stmt.setBigDecimal(7, purchaseOffer.getPrice());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            return stmt;
        }, keyHolder);
        Long newId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(newId);
    }

    @Override
    public PurchaseOffer update(PurchaseOffer purchaseOffer) {
        final String updateQuery = "update purchase_offers set seller_id = ?, customer_id = ?, status_id = ?, " +
                "product_name = ?, product_category_id = ?, product_condition_id = ?, price = ?, changed = ?, " +
                " is_deleted = ? where id = ?";
        jdbcTemplate.update(updateQuery,
                purchaseOffer.getSellerId(),
                purchaseOffer.getCustomerId(),
                purchaseOffer.getStatusId(),
                purchaseOffer.getProductName(),
                purchaseOffer.getProductCategoryId(),
                purchaseOffer.getProductConditionId(),
                purchaseOffer.getPrice(),
                Timestamp.valueOf(LocalDateTime.now()),
                purchaseOffer.getIsDeleted(),
                purchaseOffer.getId());
        return findById(purchaseOffer.getId());
    }

    @Override
    public Optional<PurchaseOffer> delete(Long id) {
        final int HOURS_IN_DAY = 24;
        final int MIN_IN_HOUR = 60;
        final int SEC_IN_MIN = 60;
        final int MILLISEC_IN_SEC = 1000;
        final int EXPIRATION_DATE = 30;
        final String deleteQuery = "update purchase_offers set changed = ?, is_deleted = true where id = ?";
        PurchaseOffer purchaseOffer = findById(id);
        long millisecondsBetweenTwoDates = purchaseOffer.getChanged().getTime() - purchaseOffer.getCreated().getTime();
        int daysBetweenDates = (int) (millisecondsBetweenTwoDates / (HOURS_IN_DAY * MIN_IN_HOUR * SEC_IN_MIN * MILLISEC_IN_SEC));
        if (daysBetweenDates >= EXPIRATION_DATE) {
            hardDelete(id);
        } else {
            jdbcTemplate.update(deleteQuery,
                    Timestamp.valueOf(LocalDateTime.now()),
                    id);
        }
        return findOne(id);
    }

    @Override
    public void hardDelete(Long id) {
        final String hardDeleteQuery = "delete from purchase_offers where id = ?";
        jdbcTemplate.update(hardDeleteQuery, id);
    }


    @Override
    public void getProductByName(String search) {
        String sql = "SELECT * FROM get_product_by_name(?)";
        try {
            List<Product> products = jdbcTemplate.query(sql, new Object[]{search}, (rs, rowNum) -> {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductCategoryId(rs.getInt("product_category_id"));
                product.setProductConditionId(rs.getInt("product_condition_id"));
                product.setPrice(rs.getBigDecimal("price"));
                return product;
            });
            logger.info(products);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            throw new EntityNotFoundException("No such id was found");
        }
    }

    @Override
    public void updateOfferPrice(Long offerId, BigDecimal newPrice) {
        String sql = "call update_offer_price(?, ?)";
        jdbcTemplate.update(sql, offerId, newPrice);
    }
}