package com.komleva.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private Long addressId;
    private String eMail;
    private String phone;
    private String login;
    private String password;
    private String userIp;
    private String hash;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public User() {
    }

    public User(Long id, String name, String surname, String gender, Long addressId, String eMail, String phone, String login, String password, String userIp, String hash, Timestamp created, Timestamp changed, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.addressId = addressId;
        this.eMail = eMail;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.userIp = userIp;
        this.hash = hash;
        this.created = created;
        this.changed = changed;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && surname.equals(user.surname) && gender.equals(user.gender) && Objects.equals(addressId, user.addressId) && eMail.equals(user.eMail) && phone.equals(user.phone) && login.equals(user.login) && password.equals(user.password) && userIp.equals(user.userIp) && hash.equals(user.hash) && created.equals(user.created) && changed.equals(user.changed) && isDeleted.equals(user.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, gender, addressId, eMail, phone, login, password, userIp, hash, created, changed, isDeleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", addressId=" + addressId +
                ", eMail='" + eMail + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userIp='" + userIp + '\'' +
                ", hash='" + hash + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
