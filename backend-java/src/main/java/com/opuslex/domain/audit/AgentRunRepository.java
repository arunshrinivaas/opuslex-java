package com.opuslex.domain.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRunRepository extends JpaRepository<AgentRun, Long> {
}
