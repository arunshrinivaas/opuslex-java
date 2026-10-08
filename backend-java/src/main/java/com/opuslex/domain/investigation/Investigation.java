package com.opuslex.domain.investigation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investigations")
public class Investigation {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "review_status")
    private String review_status;

    @Column(name = "reviewer_id")
    private Integer reviewer_id;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewed_at;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Transient
    private String documents;

    @Transient
    private String agent_runs;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getUserId() { return this.userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getReviewStatus() { return this.review_status; }
    public void setReviewStatus(String review_status) { this.review_status = review_status; }
    public Integer getReviewerId() { return this.reviewer_id; }
    public void setReviewerId(Integer reviewer_id) { this.reviewer_id = reviewer_id; }
    public LocalDateTime getReviewedAt() { return this.reviewed_at; }
    public void setReviewedAt(LocalDateTime reviewed_at) { this.reviewed_at = reviewed_at; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
    public LocalDateTime getUpdatedAt() { return this.updated_at; }
    public void setUpdatedAt(LocalDateTime updated_at) { this.updated_at = updated_at; }
    public String getDocuments() { return this.documents; }
    public void setDocuments(String documents) { this.documents = documents; }
    public String getAgentRuns() { return this.agent_runs; }
    public void setAgentRuns(String agent_runs) { this.agent_runs = agent_runs; }
}
