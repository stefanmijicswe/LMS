package org.singidunum.backend.service;

import org.springframework.transaction.annotation.Transactional;
import org.singidunum.backend.model.SubjectRealisation;
import org.singidunum.backend.model.Teacher;
import org.singidunum.backend.model.TeacherInCourse;
import org.singidunum.backend.model.TeachingType;
import org.singidunum.backend.repository.SubjectRealisationRepository;
import org.singidunum.backend.repository.TeacherInCourseRepository;
import org.singidunum.backend.repository.TeacherRepository;
import org.singidunum.backend.repository.TeachingTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherInCourseService {

    private final TeacherInCourseRepository teacherInCourseRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRealisationRepository subjectRealisationRepository;
    private final TeachingTypeRepository teachingTypeRepository;

    public TeacherInCourseService(
            TeacherInCourseRepository teacherInCourseRepository,
            TeacherRepository teacherRepository,
            SubjectRealisationRepository subjectRealisationRepository,
            TeachingTypeRepository teachingTypeRepository) {
        this.teacherInCourseRepository = teacherInCourseRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRealisationRepository = subjectRealisationRepository;
        this.teachingTypeRepository = teachingTypeRepository;
    }

    public Iterable<TeacherInCourse> findAll() {
        return teacherInCourseRepository.findAll();
    }

    public TeacherInCourse findById(Long id) {
        return teacherInCourseRepository.findById(id).orElse(null);
    }

    public TeacherInCourse save(TeacherInCourse teacherInCourse) {
        return teacherInCourseRepository.save(teacherInCourse);
    }

    public void deleteById(Long id) {
        teacherInCourseRepository.deleteById(id);
    }

    @Transactional
    public TeacherInCourse create(Long teacherId, Long subjectRealisationId, Long teachingTypeId, Integer numberOfClasses) {
        if (teacherId == null) {
            throw new RuntimeException("Teacher ID is required");
        }
        if (subjectRealisationId == null) {
            throw new RuntimeException("Subject Realisation ID is required");
        }
        if (teachingTypeId == null) {
            throw new RuntimeException("Teaching Type ID is required");
        }
        if (numberOfClasses == null) {
            throw new RuntimeException("Number of classes is required");
        }

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        SubjectRealisation subjectRealisation = subjectRealisationRepository.findById(subjectRealisationId)
                .orElseThrow(() -> new RuntimeException("Subject Realisation not found"));

        TeachingType teachingType = teachingTypeRepository.findById(teachingTypeId)
                .orElseThrow(() -> new RuntimeException("Teaching Type not found"));

        TeacherInCourse teacherInCourse = new TeacherInCourse();
        teacherInCourse.setTeacher(teacher);
        teacherInCourse.setSubjectRealisation(subjectRealisation);
        teacherInCourse.setTeachingType(teachingType);
        teacherInCourse.setNumberOfClasses(numberOfClasses);

        return teacherInCourseRepository.save(teacherInCourse);
    }

    @Transactional
    public TeacherInCourse update(Long id, Long teacherId, Long subjectRealisationId, Long teachingTypeId, Integer numberOfClasses) {
        TeacherInCourse teacherInCourse = teacherInCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TeacherInCourse not found"));

        if (teacherId != null) {
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            teacherInCourse.setTeacher(teacher);
        }

        if (subjectRealisationId != null) {
            SubjectRealisation subjectRealisation = subjectRealisationRepository.findById(subjectRealisationId)
                    .orElseThrow(() -> new RuntimeException("Subject Realisation not found"));
            teacherInCourse.setSubjectRealisation(subjectRealisation);
        }

        if (teachingTypeId != null) {
            TeachingType teachingType = teachingTypeRepository.findById(teachingTypeId)
                    .orElseThrow(() -> new RuntimeException("Teaching Type not found"));
            teacherInCourse.setTeachingType(teachingType);
        }

        if (numberOfClasses != null) {
            teacherInCourse.setNumberOfClasses(numberOfClasses);
        }

        return teacherInCourseRepository.save(teacherInCourse);
    }
}