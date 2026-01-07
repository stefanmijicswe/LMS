package org.singidunum.backend.dto;

public class StudentSubjectDTO {
    private Long id;
    private String name;
    private Integer ects;
    private Boolean mandatory;
    private Long subjectRealisationId;
    private String studyProgrammeName;
    private Integer yearNumber;

    public StudentSubjectDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getEcts() { return ects; }
    public void setEcts(Integer ects) { this.ects = ects; }

    public Boolean getMandatory() { return mandatory; }
    public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }

    public Long getSubjectRealisationId() { return subjectRealisationId; }
    public void setSubjectRealisationId(Long subjectRealisationId) { this.subjectRealisationId = subjectRealisationId; }

    public String getStudyProgrammeName() { return studyProgrammeName; }
    public void setStudyProgrammeName(String studyProgrammeName) { this.studyProgrammeName = studyProgrammeName; }

    public Integer getYearNumber() { return yearNumber; }
    public void setYearNumber(Integer yearNumber) { this.yearNumber = yearNumber; }
}