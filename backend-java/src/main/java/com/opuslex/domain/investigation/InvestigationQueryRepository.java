package com.opuslex.domain.investigation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationQueryRepository extends JpaRepository<InvestigationQuery, Integer> {
}
