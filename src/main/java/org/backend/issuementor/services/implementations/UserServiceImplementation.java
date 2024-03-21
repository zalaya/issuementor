package org.backend.issuementor.services.implementations;

import org.backend.issuementor.models.User;
import org.backend.issuementor.repositories.UserRepository;
import org.backend.issuementor.services.PasswordService;
import org.backend.issuementor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

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
