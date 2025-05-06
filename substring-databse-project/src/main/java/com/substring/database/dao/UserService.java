package com.substring.database.dao;


import com.substring.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveUser(User user) {
        // user ko save karna chah rahe hai
        String query = "insert into users values(?,?,?,?,?,?)";
        int rowsEffected = jdbcTemplate.update(
                query,
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCity(),
                user.getSalary()
        );
        System.out.println("user added: " + rowsEffected);
        return rowsEffected;
    }

}
