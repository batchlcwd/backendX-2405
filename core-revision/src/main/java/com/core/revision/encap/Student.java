package com.core.revision.encap;

public class Student {

    private String name;
    private String rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Validation logic can be added here
        if(name==null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        if(name.length() < 3){
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        }
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }
}
