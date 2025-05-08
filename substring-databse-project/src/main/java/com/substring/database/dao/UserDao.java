package com.substring.database.dao;


import com.substring.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;



}
