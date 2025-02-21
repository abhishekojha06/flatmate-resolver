package com.example.flatmate.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType;  // Enum (Noise, Cleanliness, etc.)

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;  // Enum (Minor, Major, etc.)

    private LocalDateTime timestamp = LocalDateTime.now(); // Auto-set time

    private boolean resolved = false;  // Default is false



    @Setter
    private int upvotes =0;
    @Setter
    private int downvotes = 0;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Complaint is linked to a User

    @ElementCollection
    private Set<Long> votedUsers = new HashSet<>();

}
