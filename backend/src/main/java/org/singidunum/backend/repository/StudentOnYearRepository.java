package org.singidunum.backend.repository;

import org.singidunum.backend.model.StudentOnYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface StudentOnYearRepository extends CrudRepository<StudentOnYear, Long>, PagingAndSortingRepository<StudentOnYear, Long> {

    Optional<StudentOnYear> findByStudentIdAndYearOfStudyId(Long studentId, Long yearOfStudyId);
    List<StudentOnYear> findByStudent_User_Id(Long userId);
    Optional<StudentOnYear> findFirstByStudent_User_IdOrderByEnrolmentDateDesc(Long userId);
}