package com.opuslex.domain.investigation;

import jakarta.persistence.*;

@Entity
@Table(name = "investigation_documents")
@IdClass(InvestigationDocumentId.class)
public class InvestigationDocument {
    @Id
    @Column(name = "investigation_id")
    private Integer investigation_id;

    @Id
    @Column(name = "document_id")
    private Integer document_id;
}
