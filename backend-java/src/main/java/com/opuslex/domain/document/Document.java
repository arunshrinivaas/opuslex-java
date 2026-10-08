package com.opuslex.domain.document;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "filename")
    private String filename;

    @Column(name = "file_hash")
    private String file_hash;

    @Column(name = "document_type")
    private String document_type;

    @Column(name = "jurisdiction")
    private String jurisdiction;

    @Column(name = "description")
    private String description;

    @Column(name = "extracted_text")
    private String extracted_text;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at;

    @Column(name = "investigations")
    private String investigations;

}
