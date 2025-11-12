package org.singidunum.backend.repository;

import org.singidunum.backend.model.EducationalGoal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EducationalGoalRepository extends CrudRepository<EducationalGoal, Long>, PagingAndSortingRepository<EducationalGoal, Long>{

}
