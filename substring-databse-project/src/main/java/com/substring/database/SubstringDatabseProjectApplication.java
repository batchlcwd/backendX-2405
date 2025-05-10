package com.substring.database;

import com.substring.database.dao.BookDao;
import com.substring.database.dao.IssueBookDao;
import com.substring.database.dao.UserDao;
import com.substring.database.entity.Book;
import com.substring.database.entity.IssueBook;
import com.substring.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SubstringDatabseProjectApplication implements CommandLineRunner {


    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IssueBookDao issueBookDao;

    public static void main(String[] args) {
        SpringApplication.run(SubstringDatabseProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            try {

                System.out.println("***Welcome to Library Management System***");
                System.out.println("PRESS 1 to Add a Book");
                System.out.println("PRESS 2 to View All Books");
                System.out.println("PRESS 3 to Search a Book");
                System.out.println("PRESS 4 to Update a Book");
                System.out.println("PRESS 5 to Delete a Book");
                System.out.println("PRESS 6 to View book details");
                System.out.println("PRESS 7 to Exit");

                System.out.println("Press 8 to issue book to user");
                System.out.println("Press 9 to return book from user");
                System.out.println("Press 10 to view all issued books");


                System.out.println("Enter your choice: ");
                int choice = Integer.parseInt(bufferedReader.readLine());
                if (choice == 1) {
                    /// book add-- add logic
                    System.out.println("Enter the book title: ");
                    String title = bufferedReader.readLine();

                    System.out.println("Enter the book about: ");
                    String about = bufferedReader.readLine();
                    System.out.println("Enter the book author: ");
                    String author = bufferedReader.readLine();
                    System.out.println("Enter the book language: ");
                    String language = bufferedReader.readLine();
                    System.out.println("Enter the book available:[T/F] ");
                    String available = bufferedReader.readLine();

                    System.out.println("Enter the price for day: ");
                    int price = Integer.parseInt(bufferedReader.readLine());


                    boolean isAvailable = available.equalsIgnoreCase("T");


                    Book book = new Book();
                    book.setTitle(title);
                    book.setAbout(about);
                    book.setAvailable(isAvailable);
                    book.setAuthor(author);
                    book.setLanguage(language);
                    book.setPriceOfDay(price);

                    bookDao.save(book);
                    System.out.println("Book added successfully!");
                    System.out.println("________________________________");
                    System.out.println();


                } else if (choice == 2) {
                    ///  book view-- view logic

                    System.out.println("All books in the library:");
                    System.out.println("________________________________");
                    System.out.println();
                    System.out.println("ID | Title");
                    List<Book> all = bookDao.getAll();
                    all.forEach(book -> {
                        System.out.println(book.getId() + " | " + book.getTitle());
                    });

                    System.out.println("________________________________");
                    System.out.println();


                } else if (choice == 3) {
                    /// book search-- search logic

                    System.out.println("Enter the book title to search: ");
                    String titleKeyword = bufferedReader.readLine();
                    List<Book> search = bookDao.search(titleKeyword);
                    System.out.println("ID | Title");
                    search.forEach(book -> {
                        System.out.println(book.getId() + " | " + book.getTitle());
                    });

                    System.out.println("________________________________");
                    System.out.println();


                } else if (choice == 4) {
                    /// book update-- update logic
                } else if (choice == 5) {
                    ///  delete logic

                    System.out.println("Enter the book id: ");
                    int bookId = Integer.parseInt(bufferedReader.readLine());

                    bookDao.delete(bookId);
                    System.out.println("Book deleted successfully!");
                    System.out.println("________________________________");
                    System.out.println();


                } else if (choice == 6) {
                    ///  view single book detail
                    System.out.println("Enter the book id: ");
                    int bookId = Integer.parseInt(bufferedReader.readLine());

                    Book book = bookDao.get(bookId);

                    System.out.println("_________________________________");
                    System.out.println("Book ID: " + book.getId());
                    System.out.println("Book Title: " + book.getTitle());
                    System.out.println("Book About: " + book.getAbout());
                    System.out.println("Book Author: " + book.getAuthor());
                    System.out.println("Book Language: " + book.getLanguage());
                    System.out.println("Book Available: " + (book.getAvailable() ? "Yes" : "No"));
                    System.out.println("Book Price per Day: " + book.getPriceOfDay());

                    System.out.println("_________________________________");
                    System.out.println();


                } else if (choice == 7) {
                    System.out.println("Exiting the application...");
                    break;
                } else if (choice == 8) {

                    /// /book ko issue karna hai:
                    System.out.println("Enter the book id: ");
                    int bookId = Integer.parseInt(bufferedReader.readLine());

                    Book book = bookDao.get(bookId);

                    if (!book.getAvailable()) {
                        System.out.println("book is not aviable for issue");
                        continue;
                    }


                    // book ko issue karna hai:
                    System.out.println("Enter the user id: ");
                    int userid = Integer.parseInt(bufferedReader.readLine());

                    User user = userDao.get(userid);

                    LocalDate issueDate = LocalDate.now();

                    System.out.println("Enter days to issue: ");
                    int days = Integer.parseInt(bufferedReader.readLine());

                    int totalPrice = days * book.getPriceOfDay();

                    LocalDate submitDate = issueDate.plusDays(days);

                    // lets create object of issuebook

                    IssueBook issueBook = new IssueBook();
                    issueBook.setBookId(book.getId());
                    issueBook.setUserId(user.getUserId());
                    issueBook.setIssuedDate(issueDate);
                    issueBook.setIssuedForDay(days);
                    issueBook.setPenalty(0);
                    issueBook.setTotalAmount(totalPrice);
                    issueBook.setSubmitDate(submitDate);
                    issueBook.setReturned(false);
                    // save issue book
                    int issue_bookId = issueBookDao.issueBook(issueBook);
                    System.out.println("Book issued successfully!");
                    System.out.println("Issue Book ID: " + issue_bookId);

                    book.setAvailable(false);
                    bookDao.update(book.getId(),book);


                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        }

    }
}
