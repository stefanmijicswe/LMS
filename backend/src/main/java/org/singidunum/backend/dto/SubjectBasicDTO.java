package org.singidunum.backend.dto;

public class SubjectBasicDTO {

    private Long id;
    private String name;
    private int ects;
    private boolean mandatory;
    private Integer yearOfStudy;
    private Integer academicYear;

    public SubjectBasicDTO() {
    }

    public SubjectBasicDTO(Long id, String name, int ects, boolean mandatory, Integer yearOfStudy, Integer academicYear) {
        this.id = id;
        this.name = name;
        this.ects = ects;
        this.mandatory = mandatory;
        this.yearOfStudy = yearOfStudy;
        this.academicYear = academicYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }
}
