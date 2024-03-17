DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE IF NOT EXISTS issuementor;
USE issuementor;

CREATE TABLE addresses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(150),
    city VARCHAR(100),
    country VARCHAR(100),
    postal_code VARCHAR(20)
);

CREATE TABLE companies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(100),
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(100),
    phone VARCHAR(20),
    birth_date DATE,
    creation_date TIMESTAMP,
    login_date TIMESTAMP,
    role ENUM('USER', 'ADMIN', 'TECHNICIAN'),
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    address_id BIGINT,
    company_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES addresses(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE products (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100),
                          description TEXT,
                          user_id BIGINT,
                          company_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE incidences (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    description TEXT,
    creation_date TIMESTAMP,
    priority ENUM('LOW', 'MEDIUM', 'HIGH'),
    status ENUM('OPEN', 'PENDING', 'RESOLVED', 'CLOSED'),
    product_id BIGINT,
    user_id BIGINT,
    technician_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (technician_id) REFERENCES users(id)
);

CREATE TABLE attachments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    url VARCHAR(255),
    creation_date TIMESTAMP,
    incidence_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (incidence_id) REFERENCES incidences(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE product_categories (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE comments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content TEXT,
    creation_date TIMESTAMP,
    user_id BIGINT,
    incidence_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (incidence_id) REFERENCES incidences(id)
);
