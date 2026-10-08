package com.opuslex.domain.research;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "research_queries")
public class ResearchQuery {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime created_at;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public String getQuestion() { return this.question; }
    public void setQuestion(String question) { this.question = question; }
    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
