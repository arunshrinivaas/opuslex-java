package com.opuslex.domain.investigation;

import java.io.Serializable;
import java.util.Objects;

public class InvestigationDocumentId implements Serializable {
    private Integer investigation_id;
    private Integer document_id;

    public InvestigationDocumentId() {}

    public InvestigationDocumentId(Integer investigation_id, Integer document_id) {
        this.investigation_id = investigation_id;
        this.document_id = document_id;
    }

    public Integer getInvestigation_id() { return investigation_id; }
    public void setInvestigation_id(Integer investigation_id) { this.investigation_id = investigation_id; }
    public Integer getDocument_id() { return document_id; }
    public void setDocument_id(Integer document_id) { this.document_id = document_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestigationDocumentId that = (InvestigationDocumentId) o;
        return Objects.equals(investigation_id, that.investigation_id) &&
               Objects.equals(document_id, that.document_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(investigation_id, document_id);
    }
}
