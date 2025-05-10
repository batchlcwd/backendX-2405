package com.substring.database.entity;

import java.time.LocalDate;

public class IssueBook {

    private int ibId;
    private int bookId;
    private int userId;
    private LocalDate issuedDate;
    private LocalDate submitDate;
    private int issuedForDay;
    private int totalAmount;
    private int penalty=0;
    private boolean returned = false;

    public int getIbId() {
        return ibId;
    }

    public void setIbId(int ibId) {
        this.ibId = ibId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public int getIssuedForDay() {
        return issuedForDay;
    }

    public void setIssuedForDay(int issuedForDay) {
        this.issuedForDay = issuedForDay;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
