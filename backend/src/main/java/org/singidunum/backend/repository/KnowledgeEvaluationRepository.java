package org.singidunum.backend.repository;

import org.singidunum.backend.model.KnowledgeEvaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface KnowledgeEvaluationRepository extends CrudRepository<KnowledgeEvaluation, Long>, PagingAndSortingRepository<KnowledgeEvaluation, Long>{
    List<KnowledgeEvaluation> findByExaminationPeriod_Id(Long examinationPeriodId);
}
