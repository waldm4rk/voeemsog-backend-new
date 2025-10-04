package com.voeemsog.voeemsog.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AdminTokenService {
    private final HashMap<String, Long> validTokens = new HashMap<>();

    public String generateToken(){
        String token = UUID.randomUUID().toString();
        long generationTimeInMinutes = System.currentTimeMillis() / 60000;
        validTokens.put(token, generationTimeInMinutes);
        return token;
    }

    public boolean isTokenValid(String token){
        Long tokenTime = validTokens.get(token);
        if(tokenTime == null){
            return false;
        }
        if(System.currentTimeMillis() / 60000 - 30 > tokenTime){
            invalidateToken(token);
            return false;
        }
        return true;
    }

    public void invalidateToken(String token){
        validTokens.remove(token);
    }

    public void invalidateAllTokens(){
        validTokens.clear();
    }
}
