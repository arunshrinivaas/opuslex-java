package com.opuslex.domain.compliance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
public class Policy {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "department")
    private String department;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "version")
    private String version;

    @Column(name = "effective_date")
    private LocalDateTime effective_date;

    @Column(name = "created_at")
    private LocalDateTime created_at;

}
