package com.opuslex.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opuslex.domain.user.User;
import com.opuslex.domain.user.UserRepository;
import com.opuslex.dto.auth.UserCreateDto;
import com.opuslex.dto.auth.UserLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String testEmail;

    @BeforeEach
    public void setup() {
        testEmail = UUID.randomUUID().toString() + "@test.com";
    }

    @Test
    public void testFullAuthFlowAndSecurityChecks() throws Exception {
        // Register (Role Escalation Attempt)
        UserCreateDto createDto = new UserCreateDto();
        createDto.setEmail(testEmail);
        createDto.setPassword("securepass");
        createDto.setFullName("Test User");
        createDto.setRole("admin"); // Try to self-escalate

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated());

        // Validate Role Escalation Failed
        User dbUser = userRepository.findByEmail(testEmail).orElseThrow();
        assertEquals("viewer", dbUser.getRole());
        assertNotNull(dbUser.getPasswordHash());
        assertNotEquals("securepass", dbUser.getPasswordHash()); // Hashed
        
        // Duplicate Email
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isBadRequest());

        // Login Invalid
        UserLoginDto invalidLogin = new UserLoginDto();
        invalidLogin.setEmail(testEmail);
        invalidLogin.setPassword("wrong");
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidLogin)))
                .andExpect(status().isUnauthorized());

        // Login Valid
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setEmail(testEmail);
        loginDto.setPassword("securepass");

        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> resp = objectMapper.readValue(loginResult.getResponse().getContentAsString(), Map.class);
        String token = (String) resp.get("access_token");
        assertNotNull(token);

        // /me Valid Token
        MvcResult meResult = mockMvc.perform(get("/api/v1/auth/me")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        
        Map<String, Object> meResp = objectMapper.readValue(meResult.getResponse().getContentAsString(), Map.class);
        assertEquals(testEmail, meResp.get("email"));
        assertEquals("viewer", meResp.get("role"));
        assertFalse(meResp.containsKey("password_hash")); // Should never return password hash

        // /me Invalid Token
        mockMvc.perform(get("/api/v1/auth/me")
                .header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isUnauthorized());

        // Unauthenticated Request to Protected Route
        mockMvc.perform(get("/api/v1/auth/me"))
                .andExpect(status().isUnauthorized());

        // Logout
        mockMvc.perform(post("/api/v1/auth/logout")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        // Logout Unauthenticated
        mockMvc.perform(post("/api/v1/auth/logout"))
                .andExpect(status().isUnauthorized());
                
        // Disabled User Test
        dbUser.setIsActive(false);
        userRepository.save(dbUser);
        
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isForbidden());
                
        // Existing token should also be rejected because filter fetches user from DB
        mockMvc.perform(get("/api/v1/auth/me")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }
}
