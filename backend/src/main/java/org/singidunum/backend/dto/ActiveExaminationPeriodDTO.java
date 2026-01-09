package org.singidunum.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class ActiveExaminationPeriodDTO {
    private Long examinationPeriodId;
    private String examinationPeriodName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<SubjectForRegistrationDTO> subjects;

    public ActiveExaminationPeriodDTO() {}

    public Long getExaminationPeriodId() {
        return examinationPeriodId;
    }

    public void setExaminationPeriodId(Long examinationPeriodId) {
        this.examinationPeriodId = examinationPeriodId;
    }

    public String getExaminationPeriodName() {
        return examinationPeriodName;
    }

    public void setExaminationPeriodName(String examinationPeriodName) {
        this.examinationPeriodName = examinationPeriodName;
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

    public List<SubjectForRegistrationDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectForRegistrationDTO> subjects) {
        this.subjects = subjects;
    }
}

