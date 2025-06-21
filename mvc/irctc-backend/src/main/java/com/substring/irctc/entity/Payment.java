package com.substring.irctc.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private  Booking booking;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private  PaymentStatus paymentStatus;

    private  String paymentMethod;

    private  String tansactionId;
    private LocalDateTime createdAt;
}
