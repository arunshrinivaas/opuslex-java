package com.opuslex.domain.investigation;

import java.io.Serializable;
import java.util.Objects;

public class InvestigationDocumentId implements Serializable {
    private Integer investigationId;
    private Integer documentId;

    public InvestigationDocumentId() {}

    public InvestigationDocumentId(Integer investigationId, Integer documentId) {
        this.investigationId = investigationId;
        this.documentId = documentId;
    }

    public Integer getInvestigation_id() { return investigationId; }
    public void setInvestigation_id(Integer investigationId) { this.investigationId = investigationId; }
    public Integer getDocument_id() { return documentId; }
    public void setDocument_id(Integer documentId) { this.documentId = documentId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestigationDocumentId that = (InvestigationDocumentId) o;
        return Objects.equals(investigationId, that.investigationId) &&
               Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(investigationId, documentId);
    }

    public Integer getInvestigationId() { return this.investigationId; }
    public void setInvestigationId(Integer investigationId) { this.investigationId = investigationId; }
    public Integer getDocumentId() { return this.documentId; }
    public void setDocumentId(Integer documentId) { this.documentId = documentId; }
}
