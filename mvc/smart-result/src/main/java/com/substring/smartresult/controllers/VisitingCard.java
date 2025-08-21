package com.substring.smartresult.controllers;

public class VisitingCard {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String desig;

    public VisitingCard(String name) {
        this.name = name;
    }

    public VisitingCard(String name, String phone, String email, String address, String desig) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.desig = desig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }
}
