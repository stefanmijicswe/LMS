package org.singidunum.backend.repository;

import org.singidunum.backend.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long>, PagingAndSortingRepository<Subject, Long>{

}
