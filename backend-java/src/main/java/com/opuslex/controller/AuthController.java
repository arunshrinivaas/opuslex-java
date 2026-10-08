package com.opuslex.controller;

import com.opuslex.domain.user.User;
import com.opuslex.domain.user.UserRepository;
import com.opuslex.dto.auth.AuthResponseDto;
import com.opuslex.dto.auth.UserCreateDto;
import com.opuslex.dto.auth.UserLoginDto;
import com.opuslex.security.JwtUtil;
import com.opuslex.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateDto req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("detail", "Email already registered"));
        }
        
        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole("viewer");
        user.setIsActive(true);
        user.setMfaEnabled(false);
        user.setEmailVerified(false);
        userRepository.save(user);
        
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "User registered successfully");
        resp.put("id", user.getId());
        resp.put("email", user.getEmail());
        resp.put("full_name", user.getFullName());
        resp.put("role", user.getRole());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto req) {
        User user = userRepository.findByEmail(req.getEmail()).orElse(null);
        if (user == null || user.getPasswordHash() == null || !passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("detail", "Invalid email or password"));
        }
        
        if (user.getIsActive() != null && !user.getIsActive()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("detail", "Account is disabled"));
        }
        
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        
        AuthResponseDto resp = new AuthResponseDto();
        resp.setMfaRequired(false);
        resp.setAccessToken(token);
        resp.setTokenType("bearer");
        
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("email", user.getEmail());
        userMap.put("full_name", user.getFullName());
        userMap.put("role", user.getRole());
        userMap.put("mfa_enabled", user.getMfaEnabled());
        resp.setUser(userMap);
        
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal UserPrincipal principal) {
        // Fetch fresh from db
        User user = userRepository.findById(principal.getId()).orElseThrow();
        
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("email", user.getEmail());
        userMap.put("full_name", user.getFullName());
        userMap.put("role", user.getRole());
        userMap.put("is_active", user.getIsActive());
        userMap.put("mfa_enabled", user.getMfaEnabled());
        userMap.put("has_password", user.getPasswordHash() != null);
        userMap.put("google_linked", user.getGoogleId() != null);
        userMap.put("apple_linked", user.getAppleId() != null);
        userMap.put("phone_linked", user.getPhoneNumber() != null);
        userMap.put("email_verified", user.getEmailVerified());
        return ResponseEntity.ok(userMap);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}
