package com.opuslex.dto.rag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class RagRequest {
    @NotBlank(message = "Question is required")
    private String question;
    
    @Min(1)
    @Max(20)
    private int limit = 5;
    
    private Integer investigationId;

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }

    public Integer getInvestigationId() { return investigationId; }
    public void setInvestigationId(Integer investigationId) { this.investigationId = investigationId; }
}
