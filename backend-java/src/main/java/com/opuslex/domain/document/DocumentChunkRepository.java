package com.opuslex.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, Integer> {

    List<DocumentChunk> findByDocumentIdOrderByChunkIndex(Integer documentId);

    // Phase 5B: Similarity search preparation
    // This query uses the pgvector <=> operator which computes cosine distance.
    // Ensure that pgvector's vector_cosine_ops is indexed if using HNSW.
    @Query(value = "SELECT * FROM document_chunks dc " +
            "JOIN documents d ON dc.document_id = d.id " +
            "WHERE d.user_id = :userId " +
            "AND dc.embedding <=> cast(:embedding as vector) < :threshold " +
            "ORDER BY dc.embedding <=> cast(:embedding as vector) " +
            "LIMIT :topK",
            nativeQuery = true)
    List<DocumentChunk> findSimilarChunksWithThreshold(
            @Param("embedding") float[] embedding,
            @Param("userId") Integer userId,
            @Param("threshold") double threshold,
            @Param("topK") int topK);

    @Query(value = "SELECT * FROM document_chunks dc " +
            "JOIN documents d ON dc.document_id = d.id " +
            "WHERE d.user_id = :userId " +
            "ORDER BY dc.embedding <=> cast(:embedding as vector) " +
            "LIMIT :topK",
            nativeQuery = true)
    List<DocumentChunk> findSimilarChunksTopK(
            @Param("embedding") float[] embedding,
            @Param("userId") Integer userId,
            @Param("topK") int topK);
}
