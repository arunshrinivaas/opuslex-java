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

}
