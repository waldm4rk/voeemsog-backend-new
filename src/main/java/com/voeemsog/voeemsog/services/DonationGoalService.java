package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Donation;
import com.voeemsog.voeemsog.models.DonationGoal;
import com.voeemsog.voeemsog.dto.GoalRequest;
import com.voeemsog.voeemsog.repositories.DonationGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationGoalService {
    private final DonationGoalRepository goalRepository;
    private final DonationService donationService;
    public DonationGoal createGoal(GoalRequest request) {
        // Делаем неактивной предыдущую цель
        goalRepository.getDonationGoalByActive(true).ifPresent(goal -> {
            goal.setActive(false);
            goalRepository.save(goal);
        });

        DonationGoal newGoal = new DonationGoal();
        newGoal.setTitle(request.getTitle());
        newGoal.setTargetAmount(request.getTargetAmount());
        newGoal.setActive(true);

        return goalRepository.save(newGoal);
    }
    public Optional<DonationGoal> getCurrentGoal() {
        return goalRepository.getDonationGoalByActive(true);
    }
    public DonationGoal updateGoal(GoalRequest request) {
        DonationGoal goal = goalRepository.getDonationGoalByActive(true)
                .orElseThrow(() -> new IllegalStateException("No active goal"));

        goal.setTitle(request.getTitle());
        goal.setTargetAmount(request.getTargetAmount());
        return goalRepository.save(goal);
    }
    public void clearGoal() {
        goalRepository.getDonationGoalByActive(true).ifPresent(goal -> {
            goal.setActive(false);
            goalRepository.save(goal);
        });
    }
    public boolean getDonation(Donation donation){
        DonationGoal currentGoal = goalRepository.getDonationGoalByActive(true).orElseThrow(() -> new IllegalStateException("No goal active"));
        currentGoal.setCurrentAmount(currentGoal.getCurrentAmount() + donation.getAmount());
        donationService.createDonation(donation);
        return currentGoal.getCurrentAmount() >= currentGoal.getTargetAmount();
    }
}
