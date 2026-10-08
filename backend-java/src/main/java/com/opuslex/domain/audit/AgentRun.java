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

}
