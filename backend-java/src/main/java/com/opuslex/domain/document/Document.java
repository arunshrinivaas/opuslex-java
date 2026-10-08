package com.opuslex.domain.document;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Integer user_id;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at;

    @Transient
    private String investigations;

}
