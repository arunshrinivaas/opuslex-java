package com.opuslex.domain.investigation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investigation_queries")
public class InvestigationQuery {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "investigation_id")
    private Integer investigation_id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "created_at")
    private LocalDateTime created_at;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getInvestigationId() { return this.investigation_id; }
    public void setInvestigationId(Integer investigation_id) { this.investigation_id = investigation_id; }
    public String getQuestion() { return this.question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return this.answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
