package com.komleva;

import com.komleva.domain.User;
import com.komleva.repository.UserRepository;
import com.komleva.service.UserAggregationService;
import com.komleva.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SpringTest {
    public static void main(String[] args) {
//      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.komleva");

//        Object bean = applicationContext.getBean();
//      UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
        UserRepository userRepository = applicationContext.getBean("userRepositoryImpl", UserRepository.class);
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        UserAggregationService userAggregationService = applicationContext.getBean("userAggServiceImpl", UserAggregationService.class);

        /*System.out.println(userRepository.findAll());*/
       /* System.out.println(userRepository.findById(2L));*/
        /*System.out.println(userRepository.findOne(1L));*/

        /*User newUser = new User("Artyom", "Grud", "M", "sunshine2000@gmail.com",
                "375291004583", "piximixy", "hjKhu689", "118.137.26.37");
        System.out.println(userRepository.create(newUser));*/

        /*System.out.println(userRepository.getFullNameByPhone("375295905041"));
        System.out.println(userRepository.findAllUsersByGender("F"));*/
        /*System.out.println(userService.findAll());*/
        System.out.println(userAggregationService.getUsersAndPhones());
    }
}
