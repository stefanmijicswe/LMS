package org.singidunum.backend.repository;

import org.singidunum.backend.model.EvaluationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EvaluationTypeRepository extends CrudRepository<EvaluationType, Long>, PagingAndSortingRepository<EvaluationType, Long>{

}
