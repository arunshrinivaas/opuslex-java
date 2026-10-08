package com.opuslex.domain.audit;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agent_runs")
public class AgentRun {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investigation_id")
    private Long investigation_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "question")
    private String question;

    @Column(name = "status")
    private String status;

    @Column(name = "finding")
    private String finding;

    @Column(name = "evidence")
    private String evidence;

    @Column(name = "conflicts")
    private String conflicts;

    @Column(name = "evidence_gaps")
    private String evidence_gaps;

    @Column(name = "applicable_requirements")
    private String applicable_requirements;

    @Column(name = "suggested_actions")
    private String suggested_actions;

    @Column(name = "citations")
    private String citations;

    @Column(name = "risk_score")
    private Long risk_score;

    @Column(name = "risk_level")
    private String risk_level;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "investigation")
    private String investigation;

}
