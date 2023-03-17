package com.komleva;

import com.komleva.repository.user.UserRepository;
import com.komleva.service.UserAggregationService;
import com.komleva.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    private static final Logger logger = Logger.getLogger(SpringTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komleva");
        UserRepository userRepository = applicationContext.getBean("userRepositoryJdbcTemplateImpl", UserRepository.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        UserAggregationService userAggregationService = applicationContext.getBean("userAggServiceImpl", UserAggregationService.class);

        /*logger.info(userRepository.findAll());
        System.out.println(userRepository.findById(9L));
        logger.info(userRepository.findOne(1L));
        logger.info(userRepository.findOne(2L));
        logger.info(userRepository.findById(3L));
        logger.info(userRepository.findOne(4L));*/

        /*User newUser = new User("Petya", "Chehov", "M", "poetryclub@gmail.com",
                "375337002849", "Chehov", "jekjwHj29", "118.0.0.28");
        logger.info(userRepository.create(newUser));*/
        logger.info(userRepository.delete(8L));

        logger.info(userRepository.getFullNameByPhone("375295905041"));
        /*logger.info(userRepository.findAllUsersByGender("F"));*/
        /*logger.info(userService.findAll());*/
       /* logger.info(userAggregationService.getUsersAndPhones());*/
    }
}
