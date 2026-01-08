package org.singidunum.backend.repository;

import org.singidunum.backend.model.Examination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ExaminationRepository extends CrudRepository<Examination, Long>, PagingAndSortingRepository<Examination, Long> {

    List<Examination> findByStudentOnYear_Student_User_Id(Long userId);
    Optional<Examination> findByStudentOnYear_Student_User_IdAndKnowledgeEvaluation_Id(Long userId, Long knowledgeEvaluationId);
    List<Examination> findByStudentOnYear_Id(Long studentOnYearId);

    List<Examination> findByKnowledgeEvaluation_SubjectRealisation_Id(Long subjectRealisationId);

}