package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Donation;
import com.voeemsog.voeemsog.repositories.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;
    public void createDonation(Donation donation){
        donationRepository.saveAndFlush(donation);
    }
}
