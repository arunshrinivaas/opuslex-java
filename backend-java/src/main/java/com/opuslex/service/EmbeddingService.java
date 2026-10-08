package com.opuslex.service;

import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EmbeddingService {

    private AllMiniLmL6V2EmbeddingModel model;

    @PostConstruct
    public void init() {
        this.model = new AllMiniLmL6V2EmbeddingModel();
    }

    public float[] generateEmbedding(String text) {
        // langchain4j AllMiniLmL6V2EmbeddingModel automatically normalizes the embeddings
        // and generates 384-dimensional vectors.
        return model.embed(text).content().vector();
    }
}
