package com.opuslex.domain.compliance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance")
public class Compliance {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "regulation")
    private String regulation;

    @Column(name = "description")
    private String description;

    @Column(name = "department")
    private String department;

    @Column(name = "status")
    private String status;

    @Column(name = "risk_level")
    private String risk_level;

    @Column(name = "due_date")
    private LocalDateTime due_date;

    @Column(name = "created_at")
    private LocalDateTime created_at;

}
