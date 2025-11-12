package org.singidunum.backend.repository;

import org.singidunum.backend.model.SubjectRealisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectRealisationRepository extends CrudRepository<SubjectRealisation, Long>, PagingAndSortingRepository<SubjectRealisation, Long>{

}
