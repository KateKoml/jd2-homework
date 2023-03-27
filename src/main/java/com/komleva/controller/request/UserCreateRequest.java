package com.komleva.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private Long id;
    private String name;
    private String surname;
    private String gender;
    private Long addressId;
    private String email;
    private String phone;
    private String login;
    private String password;
    private String userIP;
    private Boolean isDeleted;
}
