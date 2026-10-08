package com.opuslex.domain.investigation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationRepository extends JpaRepository<Investigation, Integer> {
    java.util.List<Investigation> findByUserId(Integer userId);
    java.util.Optional<Investigation> findByIdAndUserId(Integer id, Integer userId);
}
