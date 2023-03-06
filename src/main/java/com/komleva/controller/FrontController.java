package com.komleva.controller;

import com.komleva.domain.User;
import com.komleva.repository.UserRepositoryImpl;
import com.komleva.service.UserService;
import com.komleva.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FrontController extends HttpServlet {

    //    public FrontController() {
//        super();
//    }
    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/welcome");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            System.out.println("We are processing user request");

            List<User> users = userService.findAll();

            String collect = users.stream().map(User::getName).collect(Collectors.joining(","));

            req.setAttribute("userName", collect);
            req.setAttribute("users", users);

            dispatcher.forward(req, resp);
        }
    }
}
