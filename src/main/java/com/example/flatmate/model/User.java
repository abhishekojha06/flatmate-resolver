package com.example.flatmate.model;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private int complaintsFiled;

    @Column(name = "flat_code")
    private String flatCode;  // To group users into flats
    private int karmaPoints =0;
    private boolean bestFlatmate = false;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public enum Role{
        USER, ADMIN
    }
}

