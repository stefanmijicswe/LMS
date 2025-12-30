package org.singidunum.backend.dto;

public class UpdateTeacherInCourseDTO {
    private Long teacherId;
    private Long subjectRealisationId;
    private Long teachingTypeId;
    private Integer numberOfClasses;

    public UpdateTeacherInCourseDTO() {}

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectRealisationId() {
        return subjectRealisationId;
    }

    public void setSubjectRealisationId(Long subjectRealisationId) {
        this.subjectRealisationId = subjectRealisationId;
    }

    public Long getTeachingTypeId() {
        return teachingTypeId;
    }

    public void setTeachingTypeId(Long teachingTypeId) {
        this.teachingTypeId = teachingTypeId;
    }

    public Integer getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(Integer numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }
}