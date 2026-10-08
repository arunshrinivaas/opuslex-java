package com.opuslex.domain.compliance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
public class Policy {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getDepartment() { return this.department; }
    public void setDepartment(String department) { this.department = department; }
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }
    public String getVersion() { return this.version; }
    public void setVersion(String version) { this.version = version; }
    public LocalDateTime getEffectiveDate() { return this.effective_date; }
    public void setEffectiveDate(LocalDateTime effective_date) { this.effective_date = effective_date; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
