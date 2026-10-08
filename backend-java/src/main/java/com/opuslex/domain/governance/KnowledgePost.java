package com.opuslex.domain.governance;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "knowledge_posts")
public class KnowledgePost {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "source_citation")
    private String source_citation;

    @Column(name = "investigation_id")
    private Long investigation_id;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "author")
    private String author;

}
