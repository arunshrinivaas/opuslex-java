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
    private Integer userId;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at;

    @Transient
    private String investigations;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getFilename() { return this.filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public String getFileHash() { return this.file_hash; }
    public void setFileHash(String file_hash) { this.file_hash = file_hash; }
    public String getDocumentType() { return this.document_type; }
    public void setDocumentType(String document_type) { this.document_type = document_type; }
    public String getJurisdiction() { return this.jurisdiction; }
    public void setJurisdiction(String jurisdiction) { this.jurisdiction = jurisdiction; }
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
    public String getExtractedText() { return this.extracted_text; }
    public void setExtractedText(String extracted_text) { this.extracted_text = extracted_text; }
    public Integer getUserId() { return this.userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public LocalDateTime getUploadedAt() { return this.uploaded_at; }
    public void setUploadedAt(LocalDateTime uploaded_at) { this.uploaded_at = uploaded_at; }
    public String getInvestigations() { return this.investigations; }
    public void setInvestigations(String investigations) { this.investigations = investigations; }
}
