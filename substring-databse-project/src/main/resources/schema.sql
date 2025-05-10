CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    about TEXT,
    author VARCHAR(255),
    language VARCHAR(100),
    available BOOLEAN DEFAULT TRUE,
     price_for_day int
);


CREATE TABLE IF NOT EXISTS issued_book (
    ib_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    user_id INT,
    issued_date DATE,
    price_total int,
    issue_for_day int,
    submit_date DATE,
    penality_amount int default 0,
    returned BOOLEAN DEFAULT FALSE,

    -- Foreign key constraints
    FOREIGN KEY (book_id) REFERENCES books(id),
   FOREIGN KEY (user_id) REFERENCES users(userid)
);

