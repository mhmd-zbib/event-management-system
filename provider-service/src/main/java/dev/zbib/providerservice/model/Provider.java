package dev.zbib.providerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "providers")
public class Provider {
    @Id
    private Long id; // Same as user_id from User Service

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "provider_skills", joinColumns = @JoinColumn(name = "provider_id"))
    @Column(name = "skill")
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills = new HashSet<>();

    @Column(length = 1000)
    private String description;

    private double hourlyRate;

    private String serviceArea;

    @Column(nullable = false)
    private boolean available;

    private int yearsOfExperience;

    private String qualifications;

    private String insuranceInfo;

    @Column(nullable = false)
    private boolean verified;

    private double rating;

    private int completedJobs;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        available = true;
        verified = false;
        rating = 0.0;
        completedJobs = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 