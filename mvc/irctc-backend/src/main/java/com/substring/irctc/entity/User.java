package com.substring.irctc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

        //    username==email
    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private LocalDateTime createdAt;

//    private  UserRole userRole=UserRole.ROLE_NORMAL;


    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;


    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles= new ArrayList<>();

}
