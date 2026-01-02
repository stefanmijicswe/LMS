package org.singidunum.backend.service;

import org.singidunum.backend.model.ExaminationPeriod;
import org.singidunum.backend.model.KnowledgeEvaluation;
import org.singidunum.backend.repository.ExaminationPeriodRepository;
import org.singidunum.backend.repository.KnowledgeEvaluationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExaminationPeriodService {

    private final ExaminationPeriodRepository examinationPeriodRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;

    public ExaminationPeriodService(
            ExaminationPeriodRepository examinationPeriodRepository,
            KnowledgeEvaluationRepository knowledgeEvaluationRepository) {
        this.examinationPeriodRepository = examinationPeriodRepository;
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
    }

    public Iterable<ExaminationPeriod> findAll() {
        return examinationPeriodRepository.findAll();
    }

    public ExaminationPeriod findById(Long id) {
        return examinationPeriodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination Period not found"));
    }

    public List<ExaminationPeriod> findAllActive() {
        return examinationPeriodRepository.findByActiveTrue();
    }

    @Transactional
    public ExaminationPeriod create(
            String name,
            LocalDate startDate,
            LocalDate endDate,
            Boolean active) {

        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Name is required");
        }
        if (startDate == null) {
            throw new RuntimeException("Start date is required");
        }
        if (endDate == null) {
            throw new RuntimeException("End date is required");
        }
        if (active == null) {
            active = true;
        }

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new RuntimeException("Start date must be before end date");
        }

        ExaminationPeriod examinationPeriod = new ExaminationPeriod();
        examinationPeriod.setName(name.trim());
        examinationPeriod.setStartDate(startDate);
        examinationPeriod.setEndDate(endDate);
        examinationPeriod.setActive(active);

        return examinationPeriodRepository.save(examinationPeriod);
    }

    @Transactional
    public ExaminationPeriod update(
            Long id,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            Boolean active) {

        ExaminationPeriod examinationPeriod = examinationPeriodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination Period not found"));

        if (name != null && !name.trim().isEmpty()) {
            examinationPeriod.setName(name.trim());
        }

        if (startDate != null) {
            examinationPeriod.setStartDate(startDate);
        }

        if (endDate != null) {
            examinationPeriod.setEndDate(endDate);
        }

        LocalDate finalStartDate = examinationPeriod.getStartDate();
        LocalDate finalEndDate = examinationPeriod.getEndDate();
        if (finalStartDate != null && finalEndDate != null) {
            if (finalStartDate.isAfter(finalEndDate) || finalStartDate.isEqual(finalEndDate)) {
                throw new RuntimeException("Start date must be before end date");
            }
        }

        if (active != null) {
            examinationPeriod.setActive(active);
        }

        return examinationPeriodRepository.save(examinationPeriod);
    }

    @Transactional
    public void delete(Long id) {
        ExaminationPeriod examinationPeriod = examinationPeriodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination Period not found"));

        List<KnowledgeEvaluation> knowledgeEvaluations = knowledgeEvaluationRepository
                .findByExaminationPeriod_Id(id);

        if (knowledgeEvaluations != null && !knowledgeEvaluations.isEmpty()) {
            throw new RuntimeException("Cannot delete examination period with associated knowledge evaluations");
        }

        examinationPeriodRepository.deleteById(id);
    }
}
