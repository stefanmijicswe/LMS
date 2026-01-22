package org.singidunum.backend.repository;

import org.singidunum.backend.model.YearOfStudy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface YearOfStudyRepository extends CrudRepository<YearOfStudy, Long>, PagingAndSortingRepository<YearOfStudy, Long>{

    Iterable<YearOfStudy> findByStudyProgramme_IdOrderByYearNumberAsc(Long studyProgrammeId);
}
