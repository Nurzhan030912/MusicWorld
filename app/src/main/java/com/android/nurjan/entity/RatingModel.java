package com.android.nurjan.entity;

public class RatingModel {
    private String key;
    private String name;
    private String surname;
    private String puan;
    private String phoneNumber;
    private String password;
    private String email;

    public RatingModel(String name_txt, String email_txt, String phone_txt, String password_txt, String puan) {
        this.name = name_txt;
        this.phoneNumber = phone_txt;
        this.password = password_txt;
        this.email = email_txt;
        this.puan = puan;
    }

    public RatingModel() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getPuan() {
        return puan;
    }

    public void setPuan(String puan) {
        this.puan = puan;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
