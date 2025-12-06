package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name="donation_goals")
@Data
public class DonationGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    @Column(name="target_amount")
    private float targetAmount;
    @Column(name="current_amount")
    private float currentAmount;
    private boolean active;
}
