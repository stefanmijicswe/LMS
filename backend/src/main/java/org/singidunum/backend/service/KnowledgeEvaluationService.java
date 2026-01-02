package org.singidunum.backend.service;

import org.singidunum.backend.model.EvaluationType;
import org.singidunum.backend.model.ExaminationPeriod;
import org.singidunum.backend.model.KnowledgeEvaluation;
import org.singidunum.backend.model.SubjectRealisation;
import org.singidunum.backend.repository.EvaluationTypeRepository;
import org.singidunum.backend.repository.ExaminationPeriodRepository;
import org.singidunum.backend.repository.KnowledgeEvaluationRepository;
import org.singidunum.backend.repository.SubjectRealisationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class KnowledgeEvaluationService {

    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
    private final EvaluationTypeRepository evaluationTypeRepository;
    private final SubjectRealisationRepository subjectRealisationRepository;
    private final ExaminationPeriodRepository examinationPeriodRepository;

    public KnowledgeEvaluationService(
            KnowledgeEvaluationRepository knowledgeEvaluationRepository,
            EvaluationTypeRepository evaluationTypeRepository,
            SubjectRealisationRepository subjectRealisationRepository,
            ExaminationPeriodRepository examinationPeriodRepository) {
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
        this.evaluationTypeRepository = evaluationTypeRepository;
        this.subjectRealisationRepository = subjectRealisationRepository;
        this.examinationPeriodRepository = examinationPeriodRepository;
    }

    public Iterable<KnowledgeEvaluation> findAll() {
        return knowledgeEvaluationRepository.findAll();
    }

    public KnowledgeEvaluation findById(Long id) {
        return knowledgeEvaluationRepository.findById(id).orElse(null);
    }

    @Transactional
    public KnowledgeEvaluation create(
            Date startTime,
            Date endTime,
            Integer points,
            Long evaluationTypeId,
            Long subjectRealisationId,
            Long examinationPeriodId) {

        if (startTime == null) {
            throw new RuntimeException("Start time is required");
        }
        if (endTime == null) {
            throw new RuntimeException("End time is required");
        }
        if (points == null) {
            throw new RuntimeException("Points is required");
        }
        if (evaluationTypeId == null) {
            throw new RuntimeException("Evaluation Type ID is required");
        }
        if (subjectRealisationId == null) {
            throw new RuntimeException("Subject Realisation ID is required");
        }
        if (examinationPeriodId == null) {
            throw new RuntimeException("Examination Period ID is required");
        }

        if (startTime.after(endTime) || startTime.equals(endTime)) {
            throw new RuntimeException("Start time must be before end time");
        }

        EvaluationType evaluationType = evaluationTypeRepository.findById(evaluationTypeId)
                .orElseThrow(() -> new RuntimeException("Evaluation Type not found"));

        SubjectRealisation subjectRealisation = subjectRealisationRepository.findById(subjectRealisationId)
                .orElseThrow(() -> new RuntimeException("Subject Realisation not found"));

        ExaminationPeriod examinationPeriod = examinationPeriodRepository.findById(examinationPeriodId)
                .orElseThrow(() -> new RuntimeException("Examination Period not found"));

        LocalDate startDate = examinationPeriod.getStartDate();
        LocalDate endDate = examinationPeriod.getEndDate();
        LocalDate startTimeLocalDate = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endTimeLocalDate = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (startTimeLocalDate.isBefore(startDate) || startTimeLocalDate.isAfter(endDate)) {
            throw new RuntimeException("Start time must be within examination period dates");
        }
        if (endTimeLocalDate.isBefore(startDate) || endTimeLocalDate.isAfter(endDate)) {
            throw new RuntimeException("End time must be within examination period dates");
        }

        KnowledgeEvaluation knowledgeEvaluation = new KnowledgeEvaluation();
        knowledgeEvaluation.setStartTime(startTime);
        knowledgeEvaluation.setEndTime(endTime);
        knowledgeEvaluation.setPoints(points);
        knowledgeEvaluation.setEvaluationType(evaluationType);
        knowledgeEvaluation.setSubjectRealisation(subjectRealisation);
        knowledgeEvaluation.setExaminationPeriod(examinationPeriod);

        return knowledgeEvaluationRepository.save(knowledgeEvaluation);
    }

    @Transactional
    public KnowledgeEvaluation update(
            Long id,
            Date startTime,
            Date endTime,
            Integer points,
            Long evaluationTypeId,
            Long subjectRealisationId,
            Long examinationPeriodId) {

        KnowledgeEvaluation knowledgeEvaluation = knowledgeEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Knowledge Evaluation not found"));

        if (startTime != null) {
            knowledgeEvaluation.setStartTime(startTime);
        }
        if (endTime != null) {
            knowledgeEvaluation.setEndTime(endTime);
        }
        if (points != null) {
            knowledgeEvaluation.setPoints(points);
        }

        Date finalStartTime = knowledgeEvaluation.getStartTime();
        Date finalEndTime = knowledgeEvaluation.getEndTime();
        if (finalStartTime != null && finalEndTime != null) {
            if (finalStartTime.after(finalEndTime) || finalStartTime.equals(finalEndTime)) {
                throw new RuntimeException("Start time must be before end time");
            }
        }

        if (evaluationTypeId != null) {
            EvaluationType evaluationType = evaluationTypeRepository.findById(evaluationTypeId)
                    .orElseThrow(() -> new RuntimeException("Evaluation Type not found"));
            knowledgeEvaluation.setEvaluationType(evaluationType);
        }

        if (subjectRealisationId != null) {
            SubjectRealisation subjectRealisation = subjectRealisationRepository.findById(subjectRealisationId)
                    .orElseThrow(() -> new RuntimeException("Subject Realisation not found"));
            knowledgeEvaluation.setSubjectRealisation(subjectRealisation);
        }

        if (examinationPeriodId != null) {
            ExaminationPeriod examinationPeriod = examinationPeriodRepository.findById(examinationPeriodId)
                    .orElseThrow(() -> new RuntimeException("Examination Period not found"));
            knowledgeEvaluation.setExaminationPeriod(examinationPeriod);
        }

        ExaminationPeriod examinationPeriod = knowledgeEvaluation.getExaminationPeriod();
        if (examinationPeriod != null) {
            LocalDate startDate = examinationPeriod.getStartDate();
            LocalDate endDate = examinationPeriod.getEndDate();
            Date finalStart = knowledgeEvaluation.getStartTime();
            Date finalEnd = knowledgeEvaluation.getEndTime();

            if (finalStart != null) {
                LocalDate startTimeLocalDate = finalStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (startTimeLocalDate.isBefore(startDate) || startTimeLocalDate.isAfter(endDate)) {
                    throw new RuntimeException("Start time must be within examination period dates");
                }
            }
            if (finalEnd != null) {
                LocalDate endTimeLocalDate = finalEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (endTimeLocalDate.isBefore(startDate) || endTimeLocalDate.isAfter(endDate)) {
                    throw new RuntimeException("End time must be within examination period dates");
                }
            }
        }

        return knowledgeEvaluationRepository.save(knowledgeEvaluation);
    }
}