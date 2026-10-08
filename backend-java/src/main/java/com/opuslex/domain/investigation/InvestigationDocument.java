package com.opuslex.domain.investigation;

import jakarta.persistence.*;

@Entity
@Table(name = "investigation_documents")
@IdClass(InvestigationDocumentId.class)
public class InvestigationDocument {
    @Id
    @Column(name = "investigation_id")
    private Integer investigationId;

    @Id
    @Column(name = "document_id")
    private Integer documentId;

    public Integer getInvestigationId() { return this.investigationId; }
    public void setInvestigationId(Integer investigationId) { this.investigationId = investigationId; }
    public Integer getDocumentId() { return this.documentId; }
    public void setDocumentId(Integer documentId) { this.documentId = documentId; }
}
