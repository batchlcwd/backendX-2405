package com.substring.irctc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "train_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate runDate;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private Integer availableSeats;


    // kitni seat ki type :

    @OneToMany(mappedBy = "trainSchedule")
    private List<TrainSeat> trainSeats;


    // booking
    @OneToMany(mappedBy = "trainSchedule")
    private  List<Booking> bookings;

}
