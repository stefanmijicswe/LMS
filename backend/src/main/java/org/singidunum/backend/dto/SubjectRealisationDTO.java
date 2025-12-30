package org.singidunum.backend.dto;

public class SubjectRealisationDTO {
    private Long id;
    private Long subjectId;

    public SubjectRealisationDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}