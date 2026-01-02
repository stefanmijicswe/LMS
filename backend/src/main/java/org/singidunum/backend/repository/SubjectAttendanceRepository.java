package org.singidunum.backend.repository;

import org.singidunum.backend.model.SubjectAttendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubjectAttendanceRepository extends CrudRepository<SubjectAttendance, Long>, PagingAndSortingRepository<SubjectAttendance, Long> {
    boolean existsByStudentIdAndSubjectRealisationId(Long studentId, Long subjectRealisationId);
}