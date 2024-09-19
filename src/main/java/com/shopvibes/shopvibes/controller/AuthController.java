package com.shopvibes.shopvibes.controller;

import com.shopvibes.shopvibes.model.User;
import com.shopvibes.shopvibes.service.UserService;
import com.shopvibes.shopvibes.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");


        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı");
        }


        String token = jwtTokenUtil.generateToken(user.getEmail());


        return ResponseEntity.ok(Map.of("token", token, "user", user));
    }
}