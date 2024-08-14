package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    // dar preferencia a injeção de dependencia por construtor
    @Autowired
    private UserRepository userRepository;

    // sempre excluir código não utilizado
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    // sempre excluir código não utilizado
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
