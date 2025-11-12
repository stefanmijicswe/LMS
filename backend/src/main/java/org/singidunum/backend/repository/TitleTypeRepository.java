package org.singidunum.backend.repository;

import org.singidunum.backend.model.TitleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TitleTypeRepository extends CrudRepository<TitleType, Long>, PagingAndSortingRepository<TitleType, Long>{

}
