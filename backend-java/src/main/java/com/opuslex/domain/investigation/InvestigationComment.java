package com.opuslex.domain.investigation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investigation_comments")
public class InvestigationComment {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investigation_id")
    private Long investigation_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private LocalDateTime created_at;

}
