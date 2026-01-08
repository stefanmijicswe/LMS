package org.singidunum.backend.repository;

import org.singidunum.backend.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>, PagingAndSortingRepository<Teacher, Long> {
    boolean existsByPin(String pin);
    Optional<Teacher> findByUser_Id(Long userId);
}
