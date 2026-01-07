package org.singidunum.backend.dto;

public class ExaminationDTO {
    private Long id;
    private Integer points;
    private String note;
    private Long studentOnYearId;
    private Long knowledgeEvaluationId;

    public ExaminationDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Long getStudentOnYearId() { return studentOnYearId; }
    public void setStudentOnYearId(Long studentOnYearId) { this.studentOnYearId = studentOnYearId; }

    public Long getKnowledgeEvaluationId() { return knowledgeEvaluationId; }
    public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) { this.knowledgeEvaluationId = knowledgeEvaluationId; }
}