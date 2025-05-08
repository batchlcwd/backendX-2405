CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    about TEXT,
    author VARCHAR(255),
    language VARCHAR(100),
    available BOOLEAN DEFAULT TRUE
);
