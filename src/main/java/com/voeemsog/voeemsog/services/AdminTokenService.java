package com.voeemsog.voeemsog.services;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AdminTokenService {
    private final Set<String> validTokens = ConcurrentHashMap.newKeySet();

    public String generateToken(){
        String token = UUID.randomUUID().toString();
        validTokens.add(token);
        return token;
    }

    public boolean isTokenValid(String token){
        return validTokens.contains(token);
    }

    public void invalidateToken(String token){
        validTokens.remove(token);
    }

    public void invalidateAllTokens(){
        validTokens.clear();
    }
}
