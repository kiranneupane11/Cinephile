/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.service;

import com.mycompany.mymovielist.model.User;
import com.mycompany.mymovielist.repository.UserRepository;
import com.mycompany.mymovielist.util.PasswordService;

import java.util.Optional;

/**
 *
 * @author kiran
 */
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public AuthenticationService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public Optional<User> login(String usernameOrEmail, String password) {
        Optional<User> userOpt = userRepository.findByUsername(usernameOrEmail);
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(usernameOrEmail);
        }
        if (userOpt.isPresent() && passwordService.verifyPassword(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }

    public Optional<User> signup(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Optional.empty();
        }
        userRepository.add(user.getId(), user);
        return Optional.of(user);
    }
}

