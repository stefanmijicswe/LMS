package org.singidunum.backend.dto;

public class GradeStudentDTO {
    private Integer points;
    private String note;

    public GradeStudentDTO() {}

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}