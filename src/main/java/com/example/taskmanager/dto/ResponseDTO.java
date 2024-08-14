package com.example.taskmanager.dto;

import java.util.UUID;

// melhorar o nome da classe para algo mais descritivo
public record ResponseDTO(UUID id, String name, String token) {
}
