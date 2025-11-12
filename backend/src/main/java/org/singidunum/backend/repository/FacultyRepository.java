package org.singidunum.backend.repository;

import org.singidunum.backend.model.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FacultyRepository extends CrudRepository<Faculty, Long>, PagingAndSortingRepository<Faculty, Long>{

}
