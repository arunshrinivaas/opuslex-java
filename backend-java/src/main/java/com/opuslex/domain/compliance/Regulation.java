package com.opuslex.domain.compliance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "regulations")
public class Regulation {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "issuing_authority")
    private String issuing_authority;

    @Column(name = "jurisdiction")
    private String jurisdiction;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "effective_date")
    private LocalDateTime effective_date;

    @Column(name = "created_at")
    private LocalDateTime created_at;

}
