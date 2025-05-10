package com.substring.database.dao;

import com.substring.database.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {


    private JdbcTemplate jdbcTemplate;


    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //save the book
    public void save(Book book) {

        String query = "insert into books (title, about, author, language, available,price_for_day) values (?,?,?,?,?,?)";

        int rowsEffected = jdbcTemplate.update(
                query,
                book.getTitle(),
                book.getAbout(),
                book.getAuthor(),
                book.getLanguage(),
                book.getAvailable(),
                book.getPriceOfDay()
        );
        System.out.println("book added: " + rowsEffected);

    }


    // delete the book
    public void delete(int bookId) {
        String query = "delete from books where id = ?";
        int rowsEffected = jdbcTemplate.update(
                query,
                bookId
        );
    }

    // update the book
    public void update(int bookId, Book newBook) {
        String query = "update books set title = ?, about = ?, author = ?, language = ?, available = ? where id = ?";

        int rowsEffected = jdbcTemplate.update(
                query,
                newBook.getTitle(),
                newBook.getAbout(),
                newBook.getAuthor(),
                newBook.getLanguage(),
                newBook.getAvailable(),
                bookId);

        System.out.println("book updated: " + rowsEffected);

    }


    // get the book
    public Book get(int bookId) {

        String query = "select * from books where id = ?";

        Book book = jdbcTemplate.queryForObject(
                query,
                new BookRowMapper(),
                bookId
        );

        return book;


    }

    public List<Book> getAll() {

        String query = "select * from books";

        List<Book> books = jdbcTemplate.query(
                query,
                new BookRowMapper()
        );

        return books;

    }

    public List<Book> search(String titleKeyword) {

        String query = "select * from books where title like ?";

        List<Book> books = jdbcTemplate.query(
                query,
                new BookRowMapper(),
                "%" + titleKeyword + "%"
        );

        return books;

    }

}
