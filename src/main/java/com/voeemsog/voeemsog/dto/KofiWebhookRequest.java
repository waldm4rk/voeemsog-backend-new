package com.voeemsog.voeemsog.dto;

import lombok.Data;

@Data
public class KofiWebhookRequest {
    private String verification_token;
    private String message_id;
    private String timestamp;
    private String type;
    private boolean is_public;
    private String from_name;
    private String message;
    private String amount;
    private String url;
    private String email;
    private String currency;
    private boolean is_subscription_payment;
    private boolean is_first_subscription_payment;
    private String kofi_transaction_id;
    private String shop_items;
    private String tier_name;
    private String shipping;
    private String discord_username;
    private String discord_userid;
}