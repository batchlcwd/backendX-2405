package com.substring.irctc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentConfirmRequest {

    private String userId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private  String razorpayOrderId;
    private  String bookingId;

}
