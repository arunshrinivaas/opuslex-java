package com.opuslex.dto.rag;

import java.util.List;

public class RagResponse {
    private String question;
    private String answer;
    private List<RagSource> sources;

    public RagResponse(String question, String answer, List<RagSource> sources) {
        this.question = question;
        this.answer = answer;
        this.sources = sources;
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public List<RagSource> getSources() { return sources; }
    public void setSources(List<RagSource> sources) { this.sources = sources; }
}
