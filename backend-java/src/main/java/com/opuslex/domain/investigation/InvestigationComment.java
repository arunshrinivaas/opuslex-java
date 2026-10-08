package com.opuslex.domain.investigation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investigation_comments")
public class InvestigationComment {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "investigation_id")
    private Integer investigation_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private LocalDateTime created_at;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getInvestigationId() { return this.investigation_id; }
    public void setInvestigationId(Integer investigation_id) { this.investigation_id = investigation_id; }
    public Integer getUserId() { return this.user_id; }
    public void setUserId(Integer user_id) { this.user_id = user_id; }
    public String getText() { return this.text; }
    public void setText(String text) { this.text = text; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
