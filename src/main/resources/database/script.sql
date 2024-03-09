DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE issuementor;
USE issuementor;

CREATE TABLE address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100)
);

CREATE TABLE company (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    birth_date DATE,
    email VARCHAR(100),
    phone VARCHAR(20),
    address_id BIGINT,
    company_id BIGINT,
    creation_date DATE,
    role ENUM('admin', 'technician', 'user'),
    FOREIGN KEY (address_id) REFERENCES address(id),
    FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    manufacturer VARCHAR(100),
    category_id BIGINT,
    company_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE product_category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE incidence (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    description TEXT,
    image VARCHAR(255),
    creation_date DATE,
    priority ENUM('low', 'medium', 'high') DEFAULT 'low',
    status ENUM('open', 'in_progress', 'closed') DEFAULT 'open'
);

CREATE TABLE incidence_user (
    incidence_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (incidence_id, user_id),
    FOREIGN KEY (incidence_id) REFERENCES incidence(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE incidence_technician (
    incidence_id BIGINT,
    technician_id BIGINT,
    PRIMARY KEY (incidence_id, technician_id),
    FOREIGN KEY (incidence_id) REFERENCES incidence(id),
    FOREIGN KEY (technician_id) REFERENCES user(id)
);

CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    incidence_id BIGINT,
    user_id BIGINT,
    name varchar(100),
    description TEXT,
    creation_date DATE,
    FOREIGN KEY (incidence_id) REFERENCES incidence(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE attachment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    incidence_id BIGINT,
    name VARCHAR(255),
    url VARCHAR(255),
    FOREIGN KEY (incidence_id) REFERENCES incidence(id)
);
