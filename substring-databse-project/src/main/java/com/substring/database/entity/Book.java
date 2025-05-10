package com.substring.database.entity;

public class Book {
    private int id;
    private String title;
    private String about;
    private String author;
    private String language;
    private Boolean available = true;


    private int priceOfDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getPriceOfDay() {
        return priceOfDay;
    }

    public void setPriceOfDay(int priceOfDay) {
        this.priceOfDay = priceOfDay;
    }
}
