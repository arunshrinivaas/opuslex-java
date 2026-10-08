package com.opuslex.domain.governance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "knowledge_posts")
public class KnowledgePost {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "source_citation")
    private String source_citation;

    @Column(name = "investigation_id")
    private Integer investigation_id;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Transient
    private String author;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return this.userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public String getSourceCitation() { return this.source_citation; }
    public void setSourceCitation(String source_citation) { this.source_citation = source_citation; }
    public Integer getInvestigationId() { return this.investigation_id; }
    public void setInvestigationId(Integer investigation_id) { this.investigation_id = investigation_id; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
    public String getAuthor() { return this.author; }
    public void setAuthor(String author) { this.author = author; }
}
