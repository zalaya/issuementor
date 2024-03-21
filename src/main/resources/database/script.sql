DROP DATABASE IF EXISTS issuementor;
CREATE DATABASE IF NOT EXISTS issuementor;
USE issuementor;

SET GLOBAL time_zone = 'Europe/Madrid';

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    birth_date DATE,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    login_date TIMESTAMP,
    role ENUM('USER', 'ADMIN', 'TECHNICIAN') NOT NULL DEFAULT 'USER',
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    verified BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE incidences (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL DEFAULT 'LOW',
    status ENUM('OPEN', 'PENDING', 'IN_PROCESS', 'RESOLVED', 'CLOSED') NOT NULL DEFAULT 'OPEN',
    user_id BIGINT NOT NULL,
    technician_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (technician_id) REFERENCES users(id)
)
