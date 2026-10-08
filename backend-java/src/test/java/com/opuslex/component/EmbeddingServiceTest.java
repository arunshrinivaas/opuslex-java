package com.opuslex.component;

import com.opuslex.service.EmbeddingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmbeddingServiceTest {

    private EmbeddingService embeddingService;

    @BeforeEach
    public void setup() {
        embeddingService = new EmbeddingService();
        embeddingService.init(); // Initialize the model
    }

    @Test
    public void testEmbeddingDimensionsAndNormalization() {
        float[] embedding = embeddingService.generateEmbedding("This is a test document.");
        
        // C. Embedding dimension test
        assertEquals(384, embedding.length);
        
        // D. Embedding normalization test
        double sumSquares = 0;
        for (float v : embedding) {
            sumSquares += v * v;
        }
        
        // Length should be approximately 1.0 (normalized)
        assertTrue(Math.abs(1.0 - sumSquares) < 0.001, "Embedding is not normalized: " + sumSquares);
    }
}
