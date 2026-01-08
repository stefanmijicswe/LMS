package org.singidunum.backend.repository;

import org.singidunum.backend.model.TeacherInCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TeacherInCourseRepository extends CrudRepository<TeacherInCourse, Long>, PagingAndSortingRepository<TeacherInCourse, Long> {

    List<TeacherInCourse> findByTeacher_User_Id(Long userId);

}