package org.singidunum.backend.dto;

import java.util.List;

public class StudyProgrammeDTO {

    private Long id;
    private String name;
    private Long facultyId;
    private String description;
    private TeacherDTO coordinator;
    private List<SubjectBasicDTO> subjects;

    public StudyProgrammeDTO(Long id, String name, Long facultyId) {
        this.id = id;
        this.name = name;
        this.facultyId = facultyId;
    }

    public StudyProgrammeDTO() {
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

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeacherDTO getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(TeacherDTO coordinator) {
        this.coordinator = coordinator;
    }

    public List<SubjectBasicDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectBasicDTO> subjects) {
        this.subjects = subjects;
    }
}
