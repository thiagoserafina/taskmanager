package com.example.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // idealmente devem ser adicionados constraints de tamanho máximo e mínimo com o @Size
    // Ex: @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String title;

    // idealmente devem ser adicionados constraints de tamanho máximo e mínimo com o @Size
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
