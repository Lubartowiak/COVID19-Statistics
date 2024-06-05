package com.example.covidserver.service;

import com.example.covidserver.domain.User;
import com.example.covidserver.domain.dictionary.Role;
import com.example.covidserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveNewUser(String username, String password, Role role) {
        User founded = userRepository.findByUsername(username).orElse(null);
        if (Objects.isNull(founded)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(role);
            userRepository.save(newUser);
        }
    }
}
