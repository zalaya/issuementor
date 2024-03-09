DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE issuementor;
USE issuementor;

CREATE TABLE addresses (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           street VARCHAR(255),
                           city VARCHAR(100),
                           province VARCHAR(100),
                           postal_code VARCHAR(20),
                           country VARCHAR(100)
);

CREATE TABLE companies (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(100)
);

CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       birth_date DATE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone_number VARCHAR(20),
                       address_id BIGINT,
                       company_id BIGINT,
                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       role ENUM('admin', 'technician', 'user') NOT NULL,
                       FOREIGN KEY (address_id) REFERENCES addresses(id),
                       FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE categories (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(100) NOT NULL,
                            description TEXT
);

CREATE TABLE products (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          manufacturer VARCHAR(100),
                          acquired_date DATE,
                          category_id BIGINT NOT NULL,
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
                            user_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            title VARCHAR(100) NOT NULL,
                            description TEXT,
                            image_url VARCHAR(255),
                            incidence_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            priority ENUM('low', 'medium', 'high') DEFAULT 'low',
                            status ENUM('open', 'closed', 'in_progress') DEFAULT 'open',
                            close_date TIMESTAMP,
                            technician_id BIGINT,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (product_id) REFERENCES products(id),
                            FOREIGN KEY (technician_id) REFERENCES users(id)
);

CREATE TABLE comments (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          incidence_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          comment_text TEXT,
                          comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (incidence_id) REFERENCES incidences(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE attachments (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             incidence_id BIGINT NOT NULL,
                             file_name VARCHAR(255),
                             file_url VARCHAR(255),
                             FOREIGN KEY (incidence_id) REFERENCES incidences(id)
);

CREATE TABLE change_history (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                incidence_id BIGINT NOT NULL,
                                field_changed VARCHAR(50),
                                old_value VARCHAR(255),
                                new_value VARCHAR(255),
                                change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (incidence_id) REFERENCES incidences(id)
);
