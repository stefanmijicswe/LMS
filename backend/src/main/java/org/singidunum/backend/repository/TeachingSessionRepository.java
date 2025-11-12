package org.singidunum.backend.repository;

import org.singidunum.backend.model.TeachingSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeachingSessionRepository extends CrudRepository<TeachingSession, Long>, PagingAndSortingRepository<TeachingSession, Long>{

}
