DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE issuementor;
USE issuementor;

CREATE TABLE addresses (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    address_line VARCHAR(255),
    city VARCHAR(100)
);

CREATE TABLE companies (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE roles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    address_id INT,
    company_id INT,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (address_id) REFERENCES addresses(address_id),
    FOREIGN KEY (company_id) REFERENCES companies(company_id)
);

CREATE TABLE user_roles (
    user_role_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    role_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    acquisition_date DATE,
    category_id INT NOT NULL,
    company_id INT,
    user_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (company_id) REFERENCES companies(company_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE product_categories (
    product_category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_id INT,
    product_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE incidences (
    incidence_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    incidence_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    priority ENUM('low', 'medium', 'high') DEFAULT 'low',
    status ENUM('open', 'closed', 'in_progress') DEFAULT 'open',
    close_date TIMESTAMP,
    technician_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (technician_id) REFERENCES users(user_id)
);

CREATE TABLE comments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    incidence_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_text TEXT,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidence_id) REFERENCES incidences(incidence_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE attachments (
    attachment_id INT PRIMARY KEY AUTO_INCREMENT,
    incidence_id INT NOT NULL,
    file_name VARCHAR(255),
    file_url VARCHAR(255),
    FOREIGN KEY (incidence_id) REFERENCES incidences(incidence_id)
);

CREATE TABLE change_history (
    change_id INT PRIMARY KEY AUTO_INCREMENT,
    incidence_id INT NOT NULL,
    field_changed VARCHAR(50),
    old_value VARCHAR(255),
    new_value VARCHAR(255),
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidence_id) REFERENCES incidences(incidence_id)
);
