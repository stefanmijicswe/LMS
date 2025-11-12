package org.singidunum.backend.repository;

import org.singidunum.backend.model.KnowledgeEvaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KnowledgeEvaluationRepository extends CrudRepository<KnowledgeEvaluation, Long>, PagingAndSortingRepository<KnowledgeEvaluation, Long>{

}
