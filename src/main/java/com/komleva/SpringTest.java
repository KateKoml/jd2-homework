package com.komleva;

import com.komleva.repository.UserRepository;
import com.komleva.service.UserAggregationService;
import com.komleva.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
//      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komleva");

//        Object bean = applicationContext.getBean();
//      UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
        UserRepository userRepository = applicationContext.getBean("userRepositoryImpl", UserRepository.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        UserAggregationService userAggregationService = applicationContext.getBean("userAggServiceImpl", UserAggregationService.class);

        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findAllFemales());
        System.out.println(userService.findAll());
        System.out.println(userAggregationService.getUsersAndPhones());
    }
}
