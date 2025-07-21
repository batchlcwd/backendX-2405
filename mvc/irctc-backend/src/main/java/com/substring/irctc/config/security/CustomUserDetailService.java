package com.substring.irctc.config.security;

import com.substring.irctc.entity.User;
import com.substring.irctc.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

//    private PasswordEncoder passwordEncoder;
//
//    public CustomUserDetailService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    private UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        return customUserDetail;


        // Assuming User is an entity class that implements UserDetails
        // You can map your User entity to UserDetails here

//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}user123")
//                .roles("USER")
//                .build();
//
//        if (user.getUsername().equals(username)) {
//            return user;
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }


    }


}
