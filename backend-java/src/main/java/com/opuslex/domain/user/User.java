package com.opuslex.domain.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String password_hash;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "role")
    private String role;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "mfa_enabled")
    private Boolean mfa_enabled;

    @Column(name = "totp_secret")
    private String totp_secret;

    @Column(name = "google_id")
    private String google_id;

    @Column(name = "apple_id")
    private String apple_id;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email_verified")
    private Boolean email_verified;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Transient
    private String agent_runs;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return this.password_hash; }
    public void setPasswordHash(String password_hash) { this.password_hash = password_hash; }
    public String getFullName() { return this.full_name; }
    public void setFullName(String full_name) { this.full_name = full_name; }
    public String getRole() { return this.role; }
    public void setRole(String role) { this.role = role; }
    public Boolean getIsActive() { return this.is_active; }
    public void setIsActive(Boolean is_active) { this.is_active = is_active; }
    public Boolean getMfaEnabled() { return this.mfa_enabled; }
    public void setMfaEnabled(Boolean mfa_enabled) { this.mfa_enabled = mfa_enabled; }
    public String getTotpSecret() { return this.totp_secret; }
    public void setTotpSecret(String totp_secret) { this.totp_secret = totp_secret; }
    public String getGoogleId() { return this.google_id; }
    public void setGoogleId(String google_id) { this.google_id = google_id; }
    public String getAppleId() { return this.apple_id; }
    public void setAppleId(String apple_id) { this.apple_id = apple_id; }
    public String getPhoneNumber() { return this.phone_number; }
    public void setPhoneNumber(String phone_number) { this.phone_number = phone_number; }
    public Boolean getEmailVerified() { return this.email_verified; }
    public void setEmailVerified(Boolean email_verified) { this.email_verified = email_verified; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
    public LocalDateTime getUpdatedAt() { return this.updated_at; }
    public void setUpdatedAt(LocalDateTime updated_at) { this.updated_at = updated_at; }
    public String getAgentRuns() { return this.agent_runs; }
    public void setAgentRuns(String agent_runs) { this.agent_runs = agent_runs; }
}
