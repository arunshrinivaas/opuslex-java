package com.opuslex.domain.investigation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "investigation_documents")
public class InvestigationDocument {
    @Column(name = "investigation_id")
    private Long investigation_id;

    @Column(name = "document_id")
    private Long document_id;

}
