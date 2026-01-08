package org.singidunum.backend.service;

import org.singidunum.backend.dto.CourseStudentDTO;
import org.singidunum.backend.dto.ProfessorCourseDTO;
import org.singidunum.backend.model.*;
import org.singidunum.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final TeacherRepository teacherRepository;
    private final TeacherInCourseRepository teacherInCourseRepository;
    private final ExaminationRepository examinationRepository;
    private final UserRepository userRepository;

    public ProfessorService(
            TeacherRepository teacherRepository,
            TeacherInCourseRepository teacherInCourseRepository,
            ExaminationRepository examinationRepository,
            UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherInCourseRepository = teacherInCourseRepository;
        this.examinationRepository = examinationRepository;
        this.userRepository = userRepository;
    }

    public Teacher findByUserId(Long userId) {
        return teacherRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Teacher not found for user ID: " + userId));
    }

    public List<ProfessorCourseDTO> getProfessorCourses(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<ProfessorCourseDTO> courses = new ArrayList<>();
        List<TeacherInCourse> teacherInCourses = teacherInCourseRepository.findByTeacher_User_Id(teacher.getUser().getId());

        for (TeacherInCourse teacherInCourse : teacherInCourses) {
            SubjectRealisation subjectRealisation = teacherInCourse.getSubjectRealisation();
            if (subjectRealisation == null || subjectRealisation.getSubject() == null) {
                continue;
            }

            Subject subject = subjectRealisation.getSubject();
            YearOfStudy yearOfStudy = subject.getYearOfStudy();
            if (yearOfStudy == null) {
                continue;
            }

            StudyProgramme studyProgramme = yearOfStudy.getStudyProgramme();

            ProfessorCourseDTO dto = new ProfessorCourseDTO();
            dto.setId(subjectRealisation.getId());
            dto.setSubjectName(subject.getName());
            dto.setEcts(subject.getEcts());
            dto.setSubjectRealisationId(subjectRealisation.getId());
            dto.setYearNumber(yearOfStudy.getYearNumber());
            dto.setNumberOfClasses(teacherInCourse.getNumberOfClasses());

            if (studyProgramme != null) {
                dto.setStudyProgrammeName(studyProgramme.getName());
            }

            if (teacherInCourse.getTeachingType() != null) {
                dto.setTeachingTypeName(teacherInCourse.getTeachingType().getName());
            }

            courses.add(dto);
        }

        return courses;
    }

    public List<CourseStudentDTO> getCourseStudents(Long subjectRealisationId) {
        List<CourseStudentDTO> students = new ArrayList<>();
        List<Examination> examinations = examinationRepository.findByKnowledgeEvaluation_SubjectRealisation_Id(subjectRealisationId);

        for (Examination examination : examinations) {
            KnowledgeEvaluation knowledgeEvaluation = examination.getKnowledgeEvaluation();
            if (knowledgeEvaluation == null) {
                continue;
            }

            SubjectRealisation subjectRealisation = knowledgeEvaluation.getSubjectRealisation();
            if (subjectRealisation == null || subjectRealisation.getSubject() == null) {
                continue;
            }

            StudentOnYear studentOnYear = examination.getStudentOnYear();
            if (studentOnYear == null || studentOnYear.getStudent() == null) {
                continue;
            }

            Student student = studentOnYear.getStudent();

            CourseStudentDTO dto = new CourseStudentDTO();
            dto.setExaminationId(examination.getId());
            dto.setStudentId(student.getId());
            dto.setStudentName(student.getName());
            dto.setStudentSurname(student.getSurname());
            dto.setStudentPin(student.getPin());
            dto.setPoints(examination.getPoints());
            dto.setMaxPoints(knowledgeEvaluation.getPoints());
            dto.setNote(examination.getNote());
            dto.setSubjectName(subjectRealisation.getSubject().getName());

            if (knowledgeEvaluation.getExaminationPeriod() != null) {
                dto.setExaminationPeriodName(knowledgeEvaluation.getExaminationPeriod().getName());
            }

            if (knowledgeEvaluation.getEvaluationType() != null) {
                dto.setEvaluationTypeName(knowledgeEvaluation.getEvaluationType().getName());
            }

            students.add(dto);
        }

        return students;
    }

    @Transactional
    public Examination gradeStudent(Long examinationId, Integer points, String note, Long teacherId) {
        Examination examination = examinationRepository.findById(examinationId)
                .orElseThrow(() -> new RuntimeException("Examination not found"));

        KnowledgeEvaluation knowledgeEvaluation = examination.getKnowledgeEvaluation();
        if (knowledgeEvaluation == null) {
            throw new RuntimeException("Knowledge Evaluation not found for this examination");
        }

        SubjectRealisation subjectRealisation = knowledgeEvaluation.getSubjectRealisation();
        if (subjectRealisation == null) {
            throw new RuntimeException("Subject Realisation not found for this examination");
        }

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<TeacherInCourse> teacherInCourses = teacherInCourseRepository
                .findByTeacher_User_Id(teacher.getUser().getId());

        boolean hasAccess = teacherInCourses.stream()
                .anyMatch(tic -> tic.getSubjectRealisation() != null &&
                        tic.getSubjectRealisation().getId().equals(subjectRealisation.getId()));

        if (!hasAccess) {
            throw new RuntimeException("Teacher does not have access to grade this examination");
        }

        if (points != null) {
            if (points < 0) {
                throw new RuntimeException("Points cannot be negative");
            }
            examination.setPoints(points);
        }

        if (note != null) {
            examination.setNote(note);
        }

        return examinationRepository.save(examination);
    }
}