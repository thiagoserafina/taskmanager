package com.example.taskmanager.dto;

import java.util.UUID;

public record ResponseDTO (UUID id, String name, String token) { }
