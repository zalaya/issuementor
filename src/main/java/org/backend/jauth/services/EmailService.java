package org.backend.jauth.services;

import jakarta.mail.MessagingException;

public interface EmailService {
    void send(String email, String username, String token) throws MessagingException;
}
