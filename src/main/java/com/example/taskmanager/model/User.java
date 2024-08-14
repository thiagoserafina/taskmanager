package com.example.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // idealmente devem ser adicionados constraints de tamanho máximo e mínimo com o @Size
    // Ex: @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String name;

    // idealmente devem ser adicionados constraints de tamanho máximo e mínimo com o @Size
    // Ex: @Size(min = 3, max = 50)
    // idealmente devem ser adicionados constraints de unicidade com o @Column(unique = true)
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
