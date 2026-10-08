package com.opuslex.rag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opuslex.domain.document.Document;
import com.opuslex.domain.document.DocumentChunk;
import com.opuslex.domain.document.DocumentChunkRepository;
import com.opuslex.domain.document.DocumentRepository;
import com.opuslex.domain.investigation.Investigation;
import com.opuslex.domain.investigation.InvestigationDocument;
import com.opuslex.domain.investigation.InvestigationDocumentRepository;
import com.opuslex.domain.investigation.InvestigationRepository;
import com.opuslex.domain.user.User;
import com.opuslex.domain.user.UserRepository;
import com.opuslex.dto.rag.RagRequest;
import com.opuslex.service.EmbeddingService;
import com.opuslex.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class RagIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentChunkRepository documentChunkRepository;

    @Autowired
    private InvestigationRepository investigationRepository;

    @Autowired
    private InvestigationDocumentRepository investigationDocumentRepository;

    @Autowired
    private EmbeddingService embeddingService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private User userA;
    private User userB;
    private Document docA;
    private Document docB;
    private Investigation invA;

    @BeforeEach
    public void setup() {
        userA = new User();
        userA.setEmail("userA@example.com");
        userA.setPasswordHash("hash");
        userA.setRole("USER");
        userA.setIsActive(true);
        userA.setMfaEnabled(false);
        userA.setEmailVerified(false);
        userA.setCreatedAt(LocalDateTime.now());
        userA = userRepository.save(userA);

        userB = new User();
        userB.setEmail("userB@example.com");
        userB.setPasswordHash("hash");
        userB.setRole("USER");
        userB.setIsActive(true);
        userB.setMfaEnabled(false);
        userB.setEmailVerified(false);
        userB.setCreatedAt(LocalDateTime.now());
        userB = userRepository.save(userB);

        docA = new Document();
        docA.setUserId(userA.getId());
        docA.setTitle("Doc A Title");
        docA.setFilename("doca.pdf");
        docA.setDocumentType("pdf");
        docA.setJurisdiction("Unknown");
        docA.setUploadedAt(LocalDateTime.now());
        docA = documentRepository.save(docA);

        docB = new Document();
        docB.setUserId(userB.getId());
        docB.setTitle("Doc B Title");
        docB.setFilename("docb.pdf");
        docB.setDocumentType("pdf");
        docB.setJurisdiction("Unknown");
        docB.setUploadedAt(LocalDateTime.now());
        docB = documentRepository.save(docB);

        insertChunk(docA.getId(), 0, "This is a highly relevant document about legal compliance.");
        insertChunk(docA.getId(), 1, "Banana apple orange fruit salad.");
        insertChunk(docB.getId(), 0, "This is a highly relevant document about legal compliance.");

        invA = new Investigation();
        invA.setUserId(userA.getId());
        invA.setTitle("Investigation A");
        invA.setStatus("OPEN");
        invA.setCreatedAt(LocalDateTime.now());
        invA.setUpdatedAt(LocalDateTime.now());
        invA = investigationRepository.save(invA);

        InvestigationDocument invDocA = new InvestigationDocument();
        invDocA.setInvestigationId(invA.getId());
        invDocA.setDocumentId(docA.getId());
        investigationDocumentRepository.save(invDocA);
    }

    private String getToken(User user) {
        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    private void insertChunk(Integer documentId, Integer chunkIndex, String content) {
        float[] queryEmbedding = embeddingService.generateEmbedding(content);
        String embeddingStr = "[" + java.util.stream.IntStream.range(0, queryEmbedding.length)
                .mapToObj(i -> String.valueOf(queryEmbedding[i]))
                .collect(java.util.stream.Collectors.joining(",")) + "]";
                
        jdbcTemplate.update(
            "INSERT INTO document_chunks (document_id, chunk_index, content, embedding, created_at) " +
            "VALUES (?, ?, ?, CAST(? AS vector), ?)",
            documentId, chunkIndex, content, embeddingStr, LocalDateTime.now()
        );
    }

    @Test
    public void testGlobalRetrievalIsUserScoped() throws Exception {
        String token = getToken(userA);
        // User A asks a global question (no investigation ID)
        RagRequest request = new RagRequest();
        request.setQuestion("legal compliance");
        request.setLimit(5);

        mockMvc.perform(post("/api/v1/rag/ask")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question", is("legal compliance")))
                .andExpect(jsonPath("$.sources", hasSize(1))) // Only Doc A's good chunk
                .andExpect(jsonPath("$.sources[0].documentId", is(docA.getId())));
    }

    @Test
    public void testUserCannotAccessOthersInvestigation() throws Exception {
        String token = getToken(userB);
        RagRequest request = new RagRequest();
        request.setQuestion("legal compliance");
        request.setInvestigationId(invA.getId()); // User B requesting User A's investigation

        mockMvc.perform(post("/api/v1/rag/ask")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    private void updateChunk(Integer documentId, Integer chunkIndex, String content) {
        float[] queryEmbedding = embeddingService.generateEmbedding(content);
        String embeddingStr = "[" + java.util.stream.IntStream.range(0, queryEmbedding.length)
                .mapToObj(i -> String.valueOf(queryEmbedding[i]))
                .collect(java.util.stream.Collectors.joining(",")) + "]";

        jdbcTemplate.update(
            "UPDATE document_chunks SET content = ?, embedding = CAST(? AS vector) WHERE document_id = ? AND chunk_index = ?",
            content, embeddingStr, documentId, chunkIndex
        );
    }

    @Test
    public void testScopedRetrievalWithThresholdFallback() throws Exception {
        String token = getToken(userA);
        
        // Let's modify chunkA to be distant for this specific test
        updateChunk(docA.getId(), 0, "Unrelated engineering documentation about bridges.");

        RagRequest request = new RagRequest();
        request.setQuestion("Medical procedure and healthcare analysis"); // Completely unrelated
        request.setInvestigationId(invA.getId());

        // Since it's scoped, Pass 1 should return 0 results (threshold < 0.80)
        // Pass 2 should kick in and return the top K anyway.
        mockMvc.perform(post("/api/v1/rag/ask")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sources", hasSize(2))); // Returns both distant chunks
    }

    @Test
    public void testGlobalRetrievalNoFallback() throws Exception {
        String token = getToken(userA);
        // Modifying to be distant
        updateChunk(docA.getId(), 0, "Unrelated engineering documentation about bridges.");

        RagRequest request = new RagRequest();
        request.setQuestion("Medical procedure and healthcare analysis"); // Unrelated
        request.setLimit(5);
        // No investigation ID, so it's a global search

        mockMvc.perform(post("/api/v1/rag/ask")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                // Global search without investigation does NOT fallback. Should return empty sources.
                .andExpect(jsonPath("$.sources", hasSize(0))); 
    }

    @Test
    public void testUnauthenticated() throws Exception {
        RagRequest request = new RagRequest();
        request.setQuestion("legal compliance");

        mockMvc.perform(post("/api/v1/rag/ask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testValidationFailure() throws Exception {
        String token = getToken(userA);
        RagRequest request = new RagRequest();
        request.setQuestion(""); // Empty question

        mockMvc.perform(post("/api/v1/rag/ask")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity());
    }
}
