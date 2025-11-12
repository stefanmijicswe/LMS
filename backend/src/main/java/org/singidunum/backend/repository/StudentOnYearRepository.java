package org.singidunum.backend.repository;

import org.singidunum.backend.model.StudentOnYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentOnYearRepository extends CrudRepository<StudentOnYear, Long>, PagingAndSortingRepository<StudentOnYear, Long>{

}
