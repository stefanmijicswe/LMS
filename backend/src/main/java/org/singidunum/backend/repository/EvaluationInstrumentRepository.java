package org.singidunum.backend.repository;

import org.singidunum.backend.model.EvaluationInstrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EvaluationInstrumentRepository extends CrudRepository<EvaluationInstrument, Long>, PagingAndSortingRepository<EvaluationInstrument, Long>{

}
