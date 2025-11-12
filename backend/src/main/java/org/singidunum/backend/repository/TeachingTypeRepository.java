package org.singidunum.backend.repository;

import org.singidunum.backend.model.TeachingType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeachingTypeRepository extends CrudRepository<TeachingType, Long>, PagingAndSortingRepository<TeachingType, Long>{

}
