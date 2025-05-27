package com.substring.smartresult.entities;

import jakarta.persistence.*;

@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int marks;
    private int maxMarks;
    private String remark;
    private String grade;


    @ManyToOne
    private  Student student;


}
