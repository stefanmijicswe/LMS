package org.singidunum.backend.dto;

public class ProfessorCourseDTO {
    private Long id;
    private String subjectName;
    private Integer ects;
    private Long subjectRealisationId;
    private String studyProgrammeName;
    private Integer yearNumber;
    private String teachingTypeName;
    private Integer numberOfClasses;

    public ProfessorCourseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public Long getSubjectRealisationId() {
        return subjectRealisationId;
    }

    public void setSubjectRealisationId(Long subjectRealisationId) {
        this.subjectRealisationId = subjectRealisationId;
    }

    public String getStudyProgrammeName() {
        return studyProgrammeName;
    }

    public void setStudyProgrammeName(String studyProgrammeName) {
        this.studyProgrammeName = studyProgrammeName;
    }

    public Integer getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(Integer yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getTeachingTypeName() {
        return teachingTypeName;
    }

    public void setTeachingTypeName(String teachingTypeName) {
        this.teachingTypeName = teachingTypeName;
    }

    public Integer getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(Integer numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }
}