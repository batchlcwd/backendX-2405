package com.substring.smartresult.payload;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentForm {


    private String name;
    private String rollNumber;
    private String email;
    private String schoolName;
    private LocalDate dob;
    private String gender;

    private List<MarkForm> marks=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<MarkForm> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkForm> marks) {
        this.marks = marks;
    }
}
