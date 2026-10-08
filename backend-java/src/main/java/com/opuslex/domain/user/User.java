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

}
