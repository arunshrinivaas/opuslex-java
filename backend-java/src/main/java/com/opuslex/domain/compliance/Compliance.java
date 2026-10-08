package com.opuslex.domain.compliance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance")
public class Compliance {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getRegulation() { return this.regulation; }
    public void setRegulation(String regulation) { this.regulation = regulation; }
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
    public String getDepartment() { return this.department; }
    public void setDepartment(String department) { this.department = department; }
    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
    public String getRiskLevel() { return this.risk_level; }
    public void setRiskLevel(String risk_level) { this.risk_level = risk_level; }
    public LocalDateTime getDueDate() { return this.due_date; }
    public void setDueDate(LocalDateTime due_date) { this.due_date = due_date; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
