DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE issuementor;
USE issuementor;

CREATE TABLE Address (
    address_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100)
);

CREATE TABLE Company (
    company_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(100)
);

CREATE TABLE User (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    address_id BIGINT,
    company_id BIGINT,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role ENUM('admin', 'technician', 'user') NOT NULL,
    FOREIGN KEY (address_id) REFERENCES Address(address_id),
    FOREIGN KEY (company_id) REFERENCES Company(company_id)
);

CREATE TABLE Category (
    category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL,
    category_description TEXT
);

CREATE TABLE Product (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    acquired_date DATE,
    category_id BIGINT NOT NULL,
    company_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES Category(category_id),
    FOREIGN KEY (company_id) REFERENCES Company(company_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Product_Category (
    product_category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES Category(category_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

CREATE TABLE Incidence (
    incidence_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    incidence_name VARCHAR(100) NOT NULL,
    incidence_description TEXT,
    image_url VARCHAR(255),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    priority ENUM('low', 'medium', 'high') DEFAULT 'low',
    status ENUM('open', 'in_progress', 'closed') DEFAULT 'open'
);

CREATE TABLE Incidence_User (
    incidence_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (incidence_id, user_id),
    FOREIGN KEY (incidence_id) REFERENCES Incidence(incidence_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Incidence_Technician (
    incidence_id BIGINT,
    technician_id BIGINT,
    PRIMARY KEY (incidence_id, technician_id),
    FOREIGN KEY (incidence_id) REFERENCES Incidence(incidence_id),
    FOREIGN KEY (technician_id) REFERENCES User(user_id)
);

CREATE TABLE Comment (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    incidence_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    comment_text TEXT,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidence_id) REFERENCES Incidence(incidence_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Attachment (
    attachment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    incidence_id BIGINT NOT NULL,
    attachment_name VARCHAR(255),
    attachment_url VARCHAR(255),
    FOREIGN KEY (incidence_id) REFERENCES Incidence(incidence_id)
);

CREATE TABLE Change_History (
    change_history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    incidence_id BIGINT NOT NULL,
    field_changed VARCHAR(50),
    old_value VARCHAR(255),
    new_value VARCHAR(255),
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidence_id) REFERENCES Incidence(incidence_id)
);
