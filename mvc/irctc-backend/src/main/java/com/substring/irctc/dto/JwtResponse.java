package com.substring.irctc.dto;

import org.springframework.security.core.userdetails.UserDetails;

public record JwtResponse(
        String token,

        UserDto user
) {
}
