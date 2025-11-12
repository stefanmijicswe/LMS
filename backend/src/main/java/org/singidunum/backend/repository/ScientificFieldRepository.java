package org.singidunum.backend.repository;

import org.singidunum.backend.model.ScientificField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScientificFieldRepository extends CrudRepository<ScientificField, Long>, PagingAndSortingRepository<ScientificField, Long>{

}
