package com.opuslex.domain.integrations;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "integrations")
public class Integration {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_account_id")
    private String provider_account_id;

    @Column(name = "access_token")
    private String access_token;

    @Column(name = "refresh_token_encrypted")
    private String refresh_token_encrypted;

    @Column(name = "expires_at")
    private LocalDateTime expires_at;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "user")
    private String user;

}
