package org.singidunum.backend.repository;

import org.singidunum.backend.model.Examination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExaminationRepository extends CrudRepository<Examination, Long>, PagingAndSortingRepository<Examination, Long>{

}
