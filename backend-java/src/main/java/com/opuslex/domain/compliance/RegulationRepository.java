package com.opuslex.domain.compliance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Long> {
}
