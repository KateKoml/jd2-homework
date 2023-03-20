package com.komleva;

import com.komleva.domain.PurchaseOffer;
import com.komleva.repository.purchase_offer.PurchaseOfferRepository;
import com.komleva.repository.user.UserRepository;
import com.komleva.service.UserAggregationService;
import com.komleva.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SpringTest {
    private static final Logger logger = Logger.getLogger(SpringTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komleva");
        UserRepository userRepository = applicationContext.getBean("userRepositoryJdbcTemplateImpl", UserRepository.class);
        PurchaseOfferRepository purchaseOfferRepository = applicationContext.getBean("purchaseOfferRepositoryJdbcTemplateImpl", PurchaseOfferRepository.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        //UserAggregationService userAggregationService = applicationContext.getBean("userAggServiceImpl", UserAggregationService.class);

        //logger.info(purchaseOfferRepository.findAll());
        logger.info(purchaseOfferRepository.findOne(1L));
        logger.info(purchaseOfferRepository.findById(2L));
        //purchaseOfferRepository.getProductByName("bag");
        purchaseOfferRepository.updateOfferPrice(1L, BigDecimal.valueOf(8));
        /*PurchaseOffer newPurchase = new PurchaseOffer(15L, 17L, 1, "Sport bag",
                4, 2, 25.55);
        logger.info(purchaseOfferRepository.create(newPurchase));*/
        //logger.info(purchaseOfferRepository.update(new PurchaseOffer(3L, 15L, 17L, 2,
        //        "Sport bag",4, 2, 25.55, false)));
        //logger.info(purchaseOfferRepository.delete(4L));
        //logger.info(userRepository.findOne(4L));

        /*User newUser = new User("Petya", "Chehov", "M", "poetryclub@gmail.com",
                "375337002849", "Chehov", "jekjwHj29", "118.0.0.28");
        logger.info(userRepository.create(newUser));*/
        //logger.info(userRepository.delete(14L));
        //logger.info(userRepository.findOne(8L));
        //logger.info(userRepository.findById(6L));
        //logger.info(userRepository.getFullNameByPhone("375295906041"));
        //logger.info(userRepository.findAllUsersByGender("F"));
        //logger.info(userService.findAll());
       /* logger.info(userAggregationService.getUsersAndPhones());*/
    }
}
