package org.singidunum.backend.dto;

public class SubjectForRegistrationDTO {
    private Long subjectId;
    private String subjectName;
    private Long knowledgeEvaluationId;
    private String evaluationType;
    private Integer points;
    private Boolean isRegistered;

    public SubjectForRegistrationDTO() {}

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getKnowledgeEvaluationId() {
        return knowledgeEvaluationId;
    }

    public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) {
        this.knowledgeEvaluationId = knowledgeEvaluationId;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}

