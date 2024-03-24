package org.backend.jauth.services;

public interface PasswordService {
    String encode(String password);
    boolean validate(String rawPassword, String encodedPassword);
    boolean hasMinimumLength(String password);
    boolean hasUpperCase(String password);
    boolean hasLowerCase(String password);
    boolean hasNumber(String password);
    boolean hasSymbol(String password);
    int evaluate(String password);
}
