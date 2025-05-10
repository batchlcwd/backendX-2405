package com.substring.database.dao;

import com.substring.database.entity.IssueBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class IssueBookDao {

    private JdbcTemplate jdbcTemplate;

    public IssueBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int issueBook(IssueBook issueBook) {


        String sql = "INSERT INTO issued_book (book_id, user_id, issued_date, submit_date, issue_for_day, price_total,penality_amount,returned) VALUES (?, ?, ?, ?, ?, ?,?,?)";


        KeyHolder keyHolder = new GeneratedKeyHolder();


        jdbcTemplate.update(

                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, issueBook.getBookId());
                    ps.setInt(2, issueBook.getUserId());
                    ps.setDate(3, java.sql.Date.valueOf(issueBook.getIssuedDate()));
                    ps.setDate(4, java.sql.Date.valueOf(issueBook.getSubmitDate()));
                    ps.setInt(5, issueBook.getIssuedForDay());
                    ps.setInt(6, issueBook.getTotalAmount());
                    ps.setInt(7, issueBook.getPenalty());
                    ps.setBoolean(8, issueBook.isReturned());
                    return ps;

                }
                , keyHolder);


        return keyHolder.getKey().intValue();
    }

}
