package com.substring.irctc.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TrainDTO {

    @NotEmpty(message = "train number is required !!")
    @Size(min = 3,max = 20, message = "Invalid length of train no.")
    @Pattern(regexp = "^\\d+$",message = "Invalid no , train no contains only numbers.")
    @Id
    private String tainNo;

    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$",message = "Invalid train name. letters, spaces and hyphens are allowed")
    private String name;

//
//    @Email(message = "Invalid email")
//    private  String email;


    private String routeName;

    public String getTainNo() {
        return tainNo;
    }

    public void setTainNo(String tainNo) {
        this.tainNo = tainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
