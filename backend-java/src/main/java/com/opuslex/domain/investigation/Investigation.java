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
    private Integer user_id;

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

}
