package com.substring.irctc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRequest {
    private  int amount;
    private  String userId;
    private  String userName;
    private  String bookingId;
}
