package com.substring.irctc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trains")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Train {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String name;

    private Integer totalDistance;


    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;


    @ManyToOne
    @JoinColumn(name = "destination_station_id")
    private Station destinationStation;


//
//    @Email(message = "Invalid email")
//    private  String email;


    //train routes

    // schedules

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TrainImage trainImage;


    @OneToMany(mappedBy = "train")
    private List<TrainRoute> routes;

    @OneToMany(mappedBy = "train")
    private  List<TrainSchedule> schedules;


}
