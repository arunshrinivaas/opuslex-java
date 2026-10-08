package com.opuslex.domain.research;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchQueryRepository extends JpaRepository<ResearchQuery, Long> {
}
