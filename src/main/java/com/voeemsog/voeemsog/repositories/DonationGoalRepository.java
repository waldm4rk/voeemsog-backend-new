package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.DonationGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DonationGoalRepository extends JpaRepository<DonationGoal, UUID> {
    Optional<DonationGoal> getDonationGoalByActive(boolean active);
}
