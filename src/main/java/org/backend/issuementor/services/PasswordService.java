package org.backend.issuementor.services;

public interface PasswordService {
    String encode(String password);
    boolean validate(String rawPassword, String encodedPassword);
}
