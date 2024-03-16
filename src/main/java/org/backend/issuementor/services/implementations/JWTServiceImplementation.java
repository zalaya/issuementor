package org.backend.issuementor.services.implementations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.backend.issuementor.services.JWTService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JWTServiceImplementation implements JWTService {
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_MILLIS = TimeUnit.DAYS.toMillis(7);

    @Override
    public String generate(long id) {
        Date current = new Date();

        return Jwts.builder()
            .subject(String.valueOf(id))
            .issuedAt(current)
            .expiration(new Date(current.getTime() + EXPIRATION_MILLIS))
            .signWith(SECRET_KEY).compact();
    }

    @Override
    public boolean validate(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token) != null;
    }

    @Override
    public long getExpiration() {
        return EXPIRATION_MILLIS;
    }
}
