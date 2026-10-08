package com.opuslex.domain.governance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgePostRepository extends JpaRepository<KnowledgePost, Integer> {
    java.util.List<KnowledgePost> findByUserId(Integer userId);
}
