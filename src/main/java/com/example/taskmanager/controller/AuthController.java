package com.example.taskmanager.controller;

import com.example.taskmanager.dto.LoginRequestDTO;
import com.example.taskmanager.dto.RegisterRequestDTO;
import com.example.taskmanager.dto.ResponseDTO;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    // Não reutilizar DTOs para diferentes endpoints
    // pois cada endpoint pode ter campos diferentes ao longo do tempo
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        // A responsabilidade do controller é apenas de receber a requisição e enviar a resposta
        // Qualquer lógica de negócio deve ser feita em um service
        User user = this.repository.findByUsername(body.username()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getId(), user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    // Não reutilizar DTOs para diferentes endpoints
    // pois cada endpoint pode ter campos diferentes ao longo do tempo
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        // A responsabilidade do controller é apenas de receber a requisição e enviar a resposta
        // Qualquer lógica de negócio deve ser feita em um service
        Optional<User> user = this.repository.findByUsername(body.username());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setUsername(body.username());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getId(), newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
