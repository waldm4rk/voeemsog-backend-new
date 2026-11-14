package com.voeemsog.voeemsog.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voeemsog.voeemsog.models.Donation;
import com.voeemsog.voeemsog.models.DonationGoal;
import com.voeemsog.voeemsog.repositories.DonationRepository;
import com.voeemsog.voeemsog.services.DonationGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.voeemsog.voeemsog.dto.KofiWebhookRequest;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonationController {
    private final DonationGoalService donationService;
    private final DonationRepository donationRepository;
    @PostMapping("/kofi")
    public ResponseEntity<Void> kofiWebhook(@RequestParam("data") String dataJson) {

    try {
        JsonNode json = new ObjectMapper().readTree(dataJson);

        // Проверяем токен
        String configuredToken = System.getenv("KOFI_KEY");
        if (configuredToken == null || !configuredToken.equals(json.path("verification_token").asText())) {
            return ResponseEntity.status(401).build();
        }

        String type = json.get("type").asText();

        // Нас интересуют только Donation и Subscription (подписки тоже считаем донатами)
        if ("Donation".equals(type) || "Subscription".equals(type)) {

            boolean isPublic = json.path("is_public").asBoolean(true);
                Donation donation = new Donation();
                donation.setAmount(Float.parseFloat(json.get("amount").asText()));
                if(isPublic) {
                    donation.setName(json.path("from_name").asText("Anonymous"));
                    donation.setMessage(json.path("message").asText(""));
                }else{
                    donation.setName("Anonymous");
                    donation.setMessage("None");
                }
                donationService.getDonation(donation);
        }

        // Всегда отвечаем 200 — это важно!
        return ResponseEntity.ok().build();

    } catch (Exception e) {
        System.out.println("error");
        return ResponseEntity.ok().build(); // даже при ошибке — 200, чтобы не было повторов
    }
}

    @GetMapping("")
    public String[] getDonationInfo(@RequestParam("lang") String lang){
        DonationGoal goal = donationService.getCurrentGoal().orElseThrow();
        String[] info = new String[3];
        if(Objects.equals(lang, "ru_RU")){info[0] = goal.getTitle_ru();}
        else{info[0] = goal.getTitle();}
        info[1] = Float.toString(goal.getCurrentAmount());
        info[2] = Float.toString(goal.getTargetAmount());
        return info;
    }
}
