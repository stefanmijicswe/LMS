package org.singidunum.backend.dto;

import java.util.Date;

public class StudentActivityDTO {
    private Long id;
    private String subjectName;
    private Integer points;
    private Integer maxPoints;
    private Date startTime;
    private Date endTime;
    private String evaluationTypeName;
    private String examinationPeriodName;
    private Long knowledgeEvaluationId;

    public StudentActivityDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public Integer getMaxPoints() { return maxPoints; }
    public void setMaxPoints(Integer maxPoints) { this.maxPoints = maxPoints; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public String getEvaluationTypeName() { return evaluationTypeName; }
    public void setEvaluationTypeName(String evaluationTypeName) { this.evaluationTypeName = evaluationTypeName; }

    public String getExaminationPeriodName() { return examinationPeriodName; }
    public void setExaminationPeriodName(String examinationPeriodName) { this.examinationPeriodName = examinationPeriodName; }

    public Long getKnowledgeEvaluationId() { return knowledgeEvaluationId; }
    public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) { this.knowledgeEvaluationId = knowledgeEvaluationId; }
}