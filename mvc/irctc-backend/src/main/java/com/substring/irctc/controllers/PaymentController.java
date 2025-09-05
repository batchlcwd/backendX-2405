package com.substring.irctc.controllers;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.substring.irctc.dto.PaymentConfirmRequest;
import com.substring.irctc.dto.PaymentRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @PostMapping("/initiate")
    public ResponseEntity<?> startPayment(
            @RequestBody PaymentRequest paymentRequest
    ) throws RazorpayException {
        System.out.println(paymentRequest);
        // Payemnt Status-->

        // create order on razorpay
        RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", paymentRequest.getAmount() * 100);
        jsonObject.put("currency", "INR");
        jsonObject.put("receipt", "order_rcptid_12" + System.currentTimeMillis());
        Order razorpayOrder = razorpayClient.orders.create(jsonObject);

        System.out.println(razorpayOrder);
        Map<String, Object> response = new HashMap<>();
        response.put("amount", razorpayOrder.get("amount"));
        response.put("order_id", razorpayOrder.get("id"));
        response.put("status", razorpayOrder.get("status"));
        response.put("currency", razorpayOrder.get("currency"));
        response.put("receipt", razorpayOrder.get("receipt"));
        response.put("notes", razorpayOrder.get("notes"));
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPayment(
            @RequestBody PaymentConfirmRequest paymentConfirmRequest
    ) throws RazorpayException {

        System.out.println(paymentConfirmRequest);

        //signature  verification
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("razorpay_order_id", paymentConfirmRequest.getRazorpayOrderId());
        jsonObject.put("razorpay_payment_id", paymentConfirmRequest.getRazorpayPaymentId());
        jsonObject.put("razorpay_signature", paymentConfirmRequest.getRazorpaySignature());

        boolean verified = Utils.verifyPaymentSignature(jsonObject, keySecret);
        if (verified) {
            // get the booking using booking id:
            //update the booking payment status
            return ResponseEntity.ok().body(Map.of("message", "Payment Confirmed"));
        } else {
            return ResponseEntity.ok().body(Map.of("message", "Payment Failed"));
        }


    }
}


