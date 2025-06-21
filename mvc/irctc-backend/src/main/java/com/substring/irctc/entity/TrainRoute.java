package com.substring.irctc.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "train_route")
public class TrainRoute {


    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    private Integer stationOrder;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Integer haltMinutes;

    private Integer distanceFromSource;


}
