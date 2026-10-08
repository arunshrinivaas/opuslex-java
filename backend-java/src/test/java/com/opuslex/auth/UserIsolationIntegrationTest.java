package com.opuslex.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opuslex.domain.investigation.Investigation;
import com.opuslex.domain.investigation.InvestigationRepository;
import com.opuslex.domain.user.User;
import com.opuslex.domain.user.UserRepository;
import com.opuslex.dto.InvestigationRequestDto;
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

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIsolationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvestigationRepository investigationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String userAEmail;
    private String userBEmail;

    @BeforeEach
    public void setup() {
        userAEmail = UUID.randomUUID().toString() + "@test.com";
        userBEmail = UUID.randomUUID().toString() + "@test.com";
    }

    private String registerAndLogin(String email, String password) throws Exception {
        UserCreateDto createDto = new UserCreateDto();
        createDto.setEmail(email);
        createDto.setPassword(password);
        createDto.setFullName("Test User");

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated());

        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        MvcResult result = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> resp = objectMapper.readValue(result.getResponse().getContentAsString(), Map.class);
        return (String) resp.get("access_token");
    }

    @Test
    public void testUserIsolation() throws Exception {
        String tokenA = registerAndLogin(userAEmail, "passwordA");
        String tokenB = registerAndLogin(userBEmail, "passwordB");

        // User A creates an investigation
        InvestigationRequestDto invDto = new InvestigationRequestDto();
        invDto.setTitle("User A Investigation");
        
        MvcResult createResult = mockMvc.perform(post("/api/v1/investigations")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invDto)))
                .andExpect(status().isCreated())
                .andReturn();

        Map<String, Object> invResp = objectMapper.readValue(createResult.getResponse().getContentAsString(), Map.class);
        Integer invId = (Integer) invResp.get("id");

        // User B attempts to fetch User A's investigation
        mockMvc.perform(get("/api/v1/investigations/" + invId)
                .header("Authorization", "Bearer " + tokenB))
                .andExpect(status().isNotFound()); // The service throws ResourceNotFoundException because it uses findByIdAndUserId
    }
}
