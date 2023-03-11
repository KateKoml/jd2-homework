package com.komleva;

import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.service.UserAggregationService;
import com.komleva.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SpringTest {
    private static final Logger logger = Logger.getLogger(SpringTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komleva");
        UserRepository userRepository = applicationContext.getBean("userRepositoryJdbcTemplateImpl", UserRepository.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        UserAggregationService userAggregationService = applicationContext.getBean("userAggServiceImpl", UserAggregationService.class);

        logger.info(userRepository.findAll());
        /*System.out.println(userRepository.findById(9L));*/
        logger.info(userRepository.findOne(1L));
        logger.info(userRepository.findOne(2L));
        logger.info(userRepository.findById(3L));
        logger.info(userRepository.findOne(4L));

        /*User newUser = new User("Artyom", "Grud", "M", "sunshine2000@gmail.com",
                "375291004583", "piximixy", "hjKhu689", "118.137.26.37");
        logger.info(userRepository.create(newUser));*/

        logger.info(userRepository.getFullNameByPhone("375295906041"));
        logger.info(userRepository.findAllUsersByGender("F"));
        /*logger.info(userService.findAll());*/
        logger.info(userAggregationService.getUsersAndPhones());
    }
}
