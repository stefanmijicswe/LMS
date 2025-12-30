package org.singidunum.backend.dto;

import java.util.Date;

public class CreateKnowledgeEvaluationDTO {
    private Date startTime;
    private Date endTime;
    private Integer points;
    private Long evaluationTypeId;
    private Long subjectRealisationId;

    public CreateKnowledgeEvaluationDTO() {}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getEvaluationTypeId() {
        return evaluationTypeId;
    }

    public void setEvaluationTypeId(Long evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
    }

    public Long getSubjectRealisationId() {
        return subjectRealisationId;
    }

    public void setSubjectRealisationId(Long subjectRealisationId) {
        this.subjectRealisationId = subjectRealisationId;
    }
}