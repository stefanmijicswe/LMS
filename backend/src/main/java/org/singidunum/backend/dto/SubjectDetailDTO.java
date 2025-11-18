package org.singidunum.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class SubjectDetailDTO {

    private Long id;
    private String name;
    private int ects;
    private boolean mandatory;
    private Integer yearOfStudy;
    private Integer academicYear;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfLectures;
    private int numberOfExercises;
    private String syllabus;
    private List<TeachingMaterialDTO> teachingMaterials;
    private List<OutcomeDTO> outcomes;

    public SubjectDetailDTO() {
    }

    public SubjectDetailDTO(Long id, String name, int ects, boolean mandatory, Integer yearOfStudy, Integer academicYear, LocalDate startDate, LocalDate endDate, int numberOfLectures, int numberOfExercises, String syllabus, List<TeachingMaterialDTO> teachingMaterials, List<OutcomeDTO> outcomes) {
        this.id = id;
        this.name = name;
        this.ects = ects;
        this.mandatory = mandatory;
        this.yearOfStudy = yearOfStudy;
        this.academicYear = academicYear;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfLectures = numberOfLectures;
        this.numberOfExercises = numberOfExercises;
        this.syllabus = syllabus;
        this.teachingMaterials = teachingMaterials;
        this.outcomes = outcomes;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfLectures() {
        return numberOfLectures;
    }

    public void setNumberOfLectures(int numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public List<TeachingMaterialDTO> getTeachingMaterials() {
        return teachingMaterials;
    }

    public void setTeachingMaterials(List<TeachingMaterialDTO> teachingMaterials) {
        this.teachingMaterials = teachingMaterials;
    }

    public List<OutcomeDTO> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<OutcomeDTO> outcomes) {
        this.outcomes = outcomes;
    }
}
