package com.opuslex.domain.integrations;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "integrations")
public class Integration {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

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

    @Transient
    private String user;


    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return this.user_id; }
    public void setUserId(Integer user_id) { this.user_id = user_id; }
    public String getProvider() { return this.provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getProviderAccountId() { return this.provider_account_id; }
    public void setProviderAccountId(String provider_account_id) { this.provider_account_id = provider_account_id; }
    public String getAccessToken() { return this.access_token; }
    public void setAccessToken(String access_token) { this.access_token = access_token; }
    public String getRefreshTokenEncrypted() { return this.refresh_token_encrypted; }
    public void setRefreshTokenEncrypted(String refresh_token_encrypted) { this.refresh_token_encrypted = refresh_token_encrypted; }
    public LocalDateTime getExpiresAt() { return this.expires_at; }
    public void setExpiresAt(LocalDateTime expires_at) { this.expires_at = expires_at; }
    public String getScopes() { return this.scopes; }
    public void setScopes(String scopes) { this.scopes = scopes; }
    public LocalDateTime getCreatedAt() { return this.created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
    public LocalDateTime getUpdatedAt() { return this.updated_at; }
    public void setUpdatedAt(LocalDateTime updated_at) { this.updated_at = updated_at; }
    public String getUser() { return this.user; }
    public void setUser(String user) { this.user = user; }
}
