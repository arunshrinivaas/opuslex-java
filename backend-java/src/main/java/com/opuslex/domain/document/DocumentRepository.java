package com.opuslex.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    java.util.List<Document> findByUserId(Integer userId);
    java.util.Optional<Document> findByIdAndUserId(Integer id, Integer userId);
}
