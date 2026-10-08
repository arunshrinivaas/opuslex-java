package com.opuslex.dto.rag;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RagSource {
    private Integer documentId;
    private String documentTitle;
    private String filename;
    private Integer chunk;
    private Double distance;
    
    @JsonIgnore
    private String content;

    public RagSource(Integer documentId, String documentTitle, String filename, Integer chunk, Double distance, String content) {
        this.documentId = documentId;
        this.documentTitle = documentTitle;
        this.filename = filename;
        this.chunk = chunk;
        this.distance = distance;
        this.content = content;
    }

    public Integer getDocumentId() { return documentId; }
    public void setDocumentId(Integer documentId) { this.documentId = documentId; }

    public String getDocumentTitle() { return documentTitle; }
    public void setDocumentTitle(String documentTitle) { this.documentTitle = documentTitle; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public Integer getChunk() { return chunk; }
    public void setChunk(Integer chunk) { this.chunk = chunk; }

    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
