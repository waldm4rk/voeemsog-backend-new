package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.services.AdminTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Value("${admin.api}")
    private String configuredApiKey;
    private final AdminTokenService tokenService;
    @Autowired
    public AdminLoginController(AdminTokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request, HttpServletResponse response){
        String apiKey = request.get("apiKey");
        if(configuredApiKey.equals(apiKey)){
            String token = tokenService.generateToken();
            Cookie cookie = new Cookie("admin_token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/admin");
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid key");
    }


}
