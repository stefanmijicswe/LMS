package org.singidunum.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ExaminationPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "examinationPeriod")
    private List<KnowledgeEvaluation> knowledgeEvaluations;

    public ExaminationPeriod() {}

    public ExaminationPeriod(Long id, String name, LocalDate startDate, LocalDate endDate,
                             Boolean active, List<KnowledgeEvaluation> knowledgeEvaluations) {
        super();
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.knowledgeEvaluations = knowledgeEvaluations;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<KnowledgeEvaluation> getKnowledgeEvaluations() {
        return knowledgeEvaluations;
    }

    public void setKnowledgeEvaluations(List<KnowledgeEvaluation> knowledgeEvaluations) {
        this.knowledgeEvaluations = knowledgeEvaluations;
    }
}