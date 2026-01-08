package org.singidunum.backend.dto;

public class CourseStudentDTO {
    private Long examinationId;
    private Long studentId;
    private String studentName;
    private String studentSurname;
    private String studentPin;
    private Integer points;
    private Integer maxPoints;
    private String note;
    private String subjectName;
    private String examinationPeriodName;
    private String evaluationTypeName;

    public CourseStudentDTO() {}

    public Long getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Long examinationId) {
        this.examinationId = examinationId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentPin() {
        return studentPin;
    }

    public void setStudentPin(String studentPin) {
        this.studentPin = studentPin;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getExaminationPeriodName() {
        return examinationPeriodName;
    }

    public void setExaminationPeriodName(String examinationPeriodName) {
        this.examinationPeriodName = examinationPeriodName;
    }

    public String getEvaluationTypeName() {
        return evaluationTypeName;
    }

    public void setEvaluationTypeName(String evaluationTypeName) {
        this.evaluationTypeName = evaluationTypeName;
    }
}