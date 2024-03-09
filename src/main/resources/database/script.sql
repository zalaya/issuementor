DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE issuementor;
USE issuementor;

CREATE TABLE addresses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(100),
    country VARCHAR(100),
    postal_code VARCHAR(20)
);

CREATE TABLE companies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    birth_date DATE,
    email VARCHAR(100),
    phone VARCHAR(20),
    creation_date DATE,
    role ENUM('admin', 'technician', 'user'),
    address_id BIGINT,
    company_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES addresses(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    manufacturer VARCHAR(100),
    category_id BIGINT,
    company_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (company_id) REFERENCES companies(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE product_categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE incidences (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    description TEXT,
    image VARCHAR(255),
    creation_date DATE,
    priority ENUM('low', 'medium', 'high') DEFAULT 'low',
    status ENUM('open', 'in_progress', 'closed') DEFAULT 'open'
);

CREATE TABLE incidence_users (
    incidence_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (incidence_id, user_id),
    FOREIGN KEY (incidence_id) REFERENCES incidences(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE incidence_technicians (
    incidence_id BIGINT,
    technician_id BIGINT,
    PRIMARY KEY (incidence_id, technician_id),
    FOREIGN KEY (incidence_id) REFERENCES incidences(id),
    FOREIGN KEY (technician_id) REFERENCES users(id)
);

CREATE TABLE comments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name varchar(100),
    description TEXT,
    creation_date DATE,
    incidence_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (incidence_id) REFERENCES incidences(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE attachments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    url VARCHAR(255),
    incidence_id BIGINT,
    FOREIGN KEY (incidence_id) REFERENCES incidences(id)
);
