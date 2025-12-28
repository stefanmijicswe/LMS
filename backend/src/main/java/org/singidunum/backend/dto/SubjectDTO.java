package org.singidunum.backend.dto;

public class SubjectDTO {
    private Long id;
    private String name;
    private Integer ects;
    private Boolean mandatory;
    private Integer numberOfLectures;
    private Integer numberOfExercises;
    private Long yearOfStudyId;
    private Long prerequisiteId;
    private Integer otherTeachingTypes;
    private Integer research;
    private Integer otherClasses;
    private String syllabus;

    public SubjectDTO() {}

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

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getNumberOfLectures() {
        return numberOfLectures;
    }

    public void setNumberOfLectures(Integer numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }

    public Integer getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(Integer numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public Long getYearOfStudyId() {
        return yearOfStudyId;
    }

    public void setYearOfStudyId(Long yearOfStudyId) {
        this.yearOfStudyId = yearOfStudyId;
    }

    public Long getPrerequisiteId() {
        return prerequisiteId;
    }

    public void setPrerequisiteId(Long prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public Integer getOtherTeachingTypes() {
        return otherTeachingTypes;
    }

    public void setOtherTeachingTypes(Integer otherTeachingTypes) {
        this.otherTeachingTypes = otherTeachingTypes;
    }

    public Integer getResearch() {
        return research;
    }

    public void setResearch(Integer research) {
        this.research = research;
    }

    public Integer getOtherClasses() {
        return otherClasses;
    }

    public void setOtherClasses(Integer otherClasses) {
        this.otherClasses = otherClasses;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }
}
