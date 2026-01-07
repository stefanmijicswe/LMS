package org.singidunum.backend.dto;

public class RegisterExamDTO {
    private Long knowledgeEvaluationId;

    public RegisterExamDTO() {}

    public Long getKnowledgeEvaluationId() {
        return knowledgeEvaluationId;
    }

    public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) {
        this.knowledgeEvaluationId = knowledgeEvaluationId;
    }
}