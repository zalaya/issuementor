package org.backend.jauth.services.implementations;

import org.backend.jauth.dtos.AdvancedSafeUserDTO;
import org.backend.jauth.enumerators.Role;
import org.backend.jauth.models.User;
import org.backend.jauth.repositories.UserRepository;
import org.backend.jauth.services.JWTService;
import org.backend.jauth.services.PasswordService;
import org.backend.jauth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(String identification, String token) {
        Optional<User> databaseUser = findByUsernameOrEmail(identification);

        if (databaseUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!jwtService.validate(token) || databaseUser.get().getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<AdvancedSafeUserDTO> users = findAll().stream().map(user -> modelMapper.map(user, AdvancedSafeUserDTO.class)).toList();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String identification) {
        return userRepository.findByUsernameOrEmail(identification);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveEncoded(User user) {
        user.setPassword(passwordService.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User saveUnencoded(User user) {
        return userRepository.save(user);
    }
}
