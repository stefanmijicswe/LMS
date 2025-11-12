package org.singidunum.backend.repository;

import org.singidunum.backend.model.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TitleRepository extends CrudRepository<Title, Long>, PagingAndSortingRepository<Title, Long>{

}
