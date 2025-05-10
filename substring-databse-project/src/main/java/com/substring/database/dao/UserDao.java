package com.substring.database.dao;


import com.substring.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public User get(int userid) {

        String sql = "SELECT * FROM users WHERE userid = ?";
        User user1 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setCity(rs.getString("city"));
            user.setSalary(rs.getInt("salary"));
            return user;
        }, userid);
        return user1;

    }
}
