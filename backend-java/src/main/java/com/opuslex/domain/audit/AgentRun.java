package com.opuslex.domain.audit;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;

@Entity
@Table(name = "agent_runs")
public class AgentRun {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "investigation_id")
    private Integer investigation_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "question")
    private String question;

    @Column(name = "status")
    private String status;

    @Column(name = "finding")
    private String finding;

    @Column(name = "evidence")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode evidence;

    @Column(name = "conflicts")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode conflicts;

    @Column(name = "evidence_gaps")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode evidence_gaps;

    @Column(name = "applicable_requirements")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode applicable_requirements;

    @Column(name = "suggested_actions")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode suggested_actions;

    @Column(name = "citations")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode citations;

    @Column(name = "risk_score")
    private Integer risk_score;

    @Column(name = "risk_level")
    private String risk_level;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Transient
    private String investigation;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getInvestigationId() { return this.investigation_id; }
    public void setInvestigationId(Integer investigation_id) { this.investigation_id = investigation_id; }
    public Integer getUserId() { return this.user_id; }
    public void setUserId(Integer user_id) { this.user_id = user_id; }
    public String getQuestion() { return this.question; }
    public void setQuestion(String question) { this.question = question; }
    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
    public String getFinding() { return this.finding; }
    public void setFinding(String finding) { this.finding = finding; }
    public JsonNode getEvidence() { return this.evidence; }
    public void setEvidence(JsonNode evidence) { this.evidence = evidence; }
    public JsonNode getConflicts() { return this.conflicts; }
    public void setConflicts(JsonNode conflicts) { this.conflicts = conflicts; }
    public JsonNode getEvidenceGaps() { return this.evidence_gaps; }
    public void setEvidenceGaps(JsonNode evidence_gaps) { this.evidence_gaps = evidence_gaps; }
    public JsonNode getApplicableRequirements() { return this.applicable_requirements; }
    public void setApplicableRequirements(JsonNode applicable_requirements) { this.applicable_requirements = applicable_requirements; }
    public JsonNode getSuggestedActions() { return this.suggested_actions; }
    public void setSuggestedActions(JsonNode suggested_actions) { this.suggested_actions = suggested_actions; }
    public JsonNode getCitations() { return this.citations; }
    public void setCitations(JsonNode citations) { this.citations = citations; }
    public Integer getRiskScore() { return this.risk_score; }
    public void setRiskScore(Integer risk_score) { this.risk_score = risk_score; }
    public String getRiskLevel() { return this.risk_level; }
    public void setRiskLevel(String risk_level) { this.risk_level = risk_level; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
    public String getInvestigation() { return this.investigation; }
    public void setInvestigation(String investigation) { this.investigation = investigation; }
}
