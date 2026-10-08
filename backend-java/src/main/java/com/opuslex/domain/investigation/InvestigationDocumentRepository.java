package com.opuslex.domain.investigation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationDocumentRepository extends JpaRepository<InvestigationDocument, Integer> {
}
