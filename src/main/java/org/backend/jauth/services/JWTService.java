package org.backend.jauth.services;

public interface JWTService {
    String generate(long id);
    boolean validate(String token);
    long getExpiration();
}
