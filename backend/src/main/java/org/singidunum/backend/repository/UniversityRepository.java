package org.singidunum.backend.repository;

import org.singidunum.backend.model.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UniversityRepository extends CrudRepository<University, Long>, PagingAndSortingRepository<University, Long> {
}
