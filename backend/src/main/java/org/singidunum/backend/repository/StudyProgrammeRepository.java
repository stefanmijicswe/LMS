package org.singidunum.backend.repository;

import org.singidunum.backend.model.StudyProgramme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudyProgrammeRepository extends CrudRepository<StudyProgramme, Long>, PagingAndSortingRepository<StudyProgramme, Long>{

}
