package org.singidunum.backend.repository;

import org.singidunum.backend.model.Outcome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OutcomeRepository extends CrudRepository<Outcome, Long>, PagingAndSortingRepository<Outcome, Long>{

}
