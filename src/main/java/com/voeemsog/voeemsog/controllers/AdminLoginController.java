package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.services.AdminTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    private final AdminTokenService tokenService;
    @Autowired
    public AdminLoginController(AdminTokenService tokenService){
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request, HttpServletResponse response){
        String apiKey = request.get("apiKey");
        if(System.getenv("SECOND_API_KEY").equals(apiKey)){
            String token = tokenService.generateToken();
            Cookie cookie = new Cookie("admin_token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setAttribute("SameSite", "None");
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid key");
    }

    @PostMapping("/api")
    public ResponseEntity<?> api(@RequestBody String api){
        if(System.getenv("FIRST_API_KEY").equals(api)){
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/check-cookie")
    public ResponseEntity<?> checkCookie(@CookieValue(name = "admin_token", required = false) String token) {
        System.out.println(token);
        System.out.println(tokenService.isTokenValid(token));
        if (token == null || !tokenService.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("admin_token", "");
        cookie.setHttpOnly(true);
        // cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // удалить
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

}
