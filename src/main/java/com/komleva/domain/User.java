package com.komleva.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private String email;
    private String phone;
    private String login;
    private String password;
    private String userIP;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public User(String name, String surname, String gender, String email, String phone, String login, String password, String userIP) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.userIP = userIP;
    }

    public User(Long id, String name, String surname, String gender, String email, String phone, String login, String password, String userIP, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.userIP = userIP;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
