package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Donation;
import com.voeemsog.voeemsog.repositories.DonationRepository;
import com.voeemsog.voeemsog.services.DonationGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.voeemsog.voeemsog.dto.KofiWebhookRequest;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonationController {
    private final DonationGoalService donationService;
    private final DonationRepository donationRepository;
    @PostMapping(
            value = "/kofi",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public ResponseEntity<String> handleKofiWebhook(@ModelAttribute KofiWebhookRequest payload) {

        if (!System.getenv("KOFI_KEY").equals(payload.getVerification_token())) {
            return ResponseEntity.status(403).body("Invalid token");
        }

        donationService.getDonation(Float.parseFloat(payload.getAmount()));

        Donation donation = new Donation();
        donation.setId(UUID.fromString(payload.getMessage_id()));
        donation.setName(payload.getFrom_name());
        donation.setAmount(Float.parseFloat(payload.getAmount()));
        donation.setMessage(payload.getMessage());

        donationRepository.saveAndFlush(donation);

        return ResponseEntity.ok("OK");
    }
}
