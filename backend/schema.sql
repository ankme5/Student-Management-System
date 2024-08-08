CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    token VARCHAR(255),
    authenticated BOOLEAN,
    token_expiry DATE,
    PRIMARY KEY (id)
);

-- Create student table
CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255)
);

-- Create subject table
CREATE TABLE subject (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

-- Create student_subject join table
CREATE TABLE student_subject (
    student_id BIGINT,
    subject_id BIGINT,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (subject_id) REFERENCES subject(id)
);