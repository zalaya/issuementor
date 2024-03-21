package org.backend.issuementor.services.implementations;

import jakarta.mail.MessagingException;
import org.backend.issuementor.dtos.CredentialsDTO;
import org.backend.issuementor.dtos.TokenDTO;
import org.backend.issuementor.dtos.UnsafeUserDTO;
import org.backend.issuementor.dtos.BasicSafeUserDTO;
import org.backend.issuementor.models.User;
import org.backend.issuementor.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<?> signup(UnsafeUserDTO request) {
        if (userService.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (passwordService.evaluate(request.getPassword()) < 7) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.saveEncoded(modelMapper.map(request, User.class));

        try {
            emailService.send(user.getEmail(), user.getUsername(), jwtService.generate(user.getId()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(modelMapper.map(user, BasicSafeUserDTO.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(CredentialsDTO request) {
        Optional<User> databaseUser = userService.findByUsernameOrEmail(request.getIdentification());

        if (databaseUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!databaseUser.get().isVerified()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        databaseUser.get().setLoginDate(Timestamp.from(Instant.now()));
        userService.saveUnencoded(databaseUser.get());

        return new ResponseEntity<>(new TokenDTO(jwtService.generate(databaseUser.get().getId()), jwtService.getExpiration()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> verify(String email, String token) {
        Optional<User> databaseUser = userService.findByEmail(email);

        if (databaseUser.isEmpty() || !jwtService.validate(token)) {
            return new ResponseEntity<>("Verification failed.", HttpStatus.UNAUTHORIZED);
        }

        databaseUser.get().setVerified(true);
        userService.saveEncoded(databaseUser.get());

        return new ResponseEntity<>("Verification succeeded", HttpStatus.OK);
    }
}
