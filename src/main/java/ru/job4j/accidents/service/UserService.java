package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthorityService authorityService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> save(User user) {
        Optional<User> savedUser = Optional.empty();
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findAuthorityByName("ROLE_USER"));
        try {
            savedUser = Optional.of(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Failed to save user", e);
        }
        return savedUser;
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }
}
