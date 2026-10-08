package com.opuslex.domain.document;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_chunks")
public class DocumentChunk {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document_id")
    private Integer documentId;

    @Column(name = "chunk_index")
    private Integer chunkIndex;

    @Column(name = "content")
    private String content;

    @Column(name = "embedding", columnDefinition = "vector(384)")
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.VECTOR)
    private float[] embedding;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getDocumentId() { return this.documentId; }
    public void setDocumentId(Integer documentId) { this.documentId = documentId; }
    public Integer getChunkIndex() { return this.chunkIndex; }
    public void setChunkIndex(Integer chunkIndex) { this.chunkIndex = chunkIndex; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public float[] getEmbedding() { return this.embedding; }
    public void setEmbedding(float[] embedding) { this.embedding = embedding; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
