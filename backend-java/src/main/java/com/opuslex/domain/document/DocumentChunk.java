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
    private Integer document_id;

    @Column(name = "chunk_index")
    private Integer chunk_index;

    @Column(name = "content")
    private String content;

    @Column(name = "embedding", columnDefinition = "vector")
    private String embedding;

    @Column(name = "created_at")
    private LocalDateTime created_at;

}
