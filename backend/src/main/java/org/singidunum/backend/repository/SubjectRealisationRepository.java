package org.singidunum.backend.repository;

import org.singidunum.backend.model.SubjectRealisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubjectRealisationRepository extends CrudRepository<SubjectRealisation, Long>, PagingAndSortingRepository<SubjectRealisation, Long>{

    List<SubjectRealisation> findBySubject_YearOfStudy_StudyProgramme_Id(Long studyProgrammeId);

}
