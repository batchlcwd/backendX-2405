package com.substring.irctc.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private JwtHelper jwtHelper;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //har ek request se pahle.

        //Bearer 213534cdslkhfafjwfghjsfgopjfpk[f
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // Extracting the token from the header

            try {
                token = authorizationHeader.substring(7); // Remove "Bearer " prefix
                username = jwtHelper.getUsernameFromToken(token);


                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if(jwtHelper.isTokenValid(token,userDetails)){

                       //SeucrityContext:

                        UsernamePasswordAuthenticationToken authentication=
                                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    }


                }


            }
            catch (IllegalArgumentException ex){
                System.out.println("Unable to get JWT Token");
                ex.printStackTrace();
            }catch (ExpiredJwtException ex){
                System.out.println("JWT Token has expired");
                ex.printStackTrace();
            }catch (MalformedJwtException ex){
                System.out.println("Invalid JWT Token");
                ex.printStackTrace();
            }
            catch (Exception e) {
                System.out.println("Invalid Token");
                e.printStackTrace();
            }


            // Check if the token is valid


        } else {
            System.out.println("Invalid Authorization Header");
        }

        //ye request ko age bhej dega
        filterChain.doFilter(request, response);


    }
}
