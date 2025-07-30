package com.substring.irctc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class
Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    private  String pnr;

    @ManyToOne
    @JoinColumn(name = "train_schedule_id")
    private  TrainSchedule trainSchedule;

    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private  Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destination_station_id")
    private  Station destinationStation;

    private LocalDate journeyDate;

    private BigDecimal totalFare;

    @Enumerated(EnumType.STRING)
    private  BookingStatus bookingStatus;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    private List<BookingPassenger> passengers;

    @OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
    private  Payment payment;


}
