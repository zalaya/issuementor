package org.backend.issuementor.services.implementations;

import org.backend.issuementor.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImplementation implements PasswordService {
    private static final int MIN_LENGTH = 8;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean validate(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean hasMinimumLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    @Override
    public boolean hasUpperCase(String password) {
        return password.matches(".*[A-Z].*");
    }

    @Override
    public boolean hasLowerCase(String password) {
        return password.matches(".*[a-z].*");
    }

    @Override
    public boolean hasNumber(String password) {
        return password.matches(".*[0-9].*");
    }

    @Override
    public boolean hasSymbol(String password) {
        return password.matches(".*[!@#$%^&*].*");
    }

    @Override
    public int evaluate(String password) {
        int score = 0;

        boolean[] criteria = {
            hasMinimumLength(password),
            hasUpperCase(password),
            hasLowerCase(password),
            hasNumber(password),
            hasSymbol(password)
        };

        for (int i = 0; i < criteria.length; i++) {
            score += criteria[i] ? 2 : 1;
        }

        return score;
    }
}
