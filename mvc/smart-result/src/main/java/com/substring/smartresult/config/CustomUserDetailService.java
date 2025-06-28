package com.substring.smartresult.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService  implements UserDetailsService {

    //private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // load user from database
                UserDetails user1 = User.builder()
                .username("sahil")
                .password("{noop}sahi123")
                .build();

//        UserDetails user2 = User.builder()
//                .username("ravi")
//                .password("{noop}ravi123")
//                .build();


        return user1;


//        return null;
    }

}
