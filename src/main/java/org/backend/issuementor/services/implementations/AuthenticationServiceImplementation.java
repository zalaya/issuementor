package org.backend.issuementor.services.implementations;

import jakarta.mail.MessagingException;
import org.backend.issuementor.dtos.LoginRequestDTO;
import org.backend.issuementor.dtos.LoginResponseDTO;
import org.backend.issuementor.dtos.SignupRequestDTO;
import org.backend.issuementor.dtos.SignupResponseDTO;
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
    public ResponseEntity<?> signup(SignupRequestDTO request) {
        if (userService.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (passwordService.evaluate(request.getPassword()) < 7) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.saveEncoded(modelMapper.map(request, User.class));

        try {
            emailService.send(user.getEmail(), jwtService.generate(user.getId()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(
            modelMapper.map(
                user,
                SignupResponseDTO.class
            ),
            HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDTO request) {
        Optional<User> user = userService.findByUsernameOrEmail(request.getIdentification());

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.get().setLoginDate(Timestamp.from(Instant.now()));

        userService.saveUnencoded(user.get());

        return new ResponseEntity<>(
            new LoginResponseDTO(
                jwtService.generate(user.get().getId()),
                jwtService.getExpiration()
            ),
            HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<?> verify(String email, String token) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isEmpty() || !jwtService.validate(token)) {
            return new ResponseEntity<>("Verification failed.", HttpStatus.UNAUTHORIZED);
        }

        user.get().setVerified(true);
        userService.saveEncoded(user.get());

        return new ResponseEntity<>("Verification succeeded", HttpStatus.OK);
    }
}
