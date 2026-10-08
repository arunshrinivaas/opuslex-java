package com.opuslex.domain.investigation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationCommentRepository extends JpaRepository<InvestigationComment, Integer> {
}
