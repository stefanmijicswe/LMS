package org.singidunum.backend.service;

import org.singidunum.backend.dto.StudentSubjectDTO;
import org.singidunum.backend.dto.StudentActivityDTO;
import org.singidunum.backend.model.*;
import org.singidunum.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentOnYearRepository studentOnYearRepository;
    private final ExaminationRepository examinationRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
    private final UserRepository userRepository;

    public StudentService(
            StudentRepository studentRepository,
            StudentOnYearRepository studentOnYearRepository,
            ExaminationRepository examinationRepository,
            KnowledgeEvaluationRepository knowledgeEvaluationRepository,
            UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.studentOnYearRepository = studentOnYearRepository;
        this.examinationRepository = examinationRepository;
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findByUserId(Long userId) {
        return studentRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Student not found for user ID: " + userId));
    }

    @Transactional
    public List<StudentSubjectDTO> getStudentSubjects(Long studentId) {
        Student student = findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        List<StudentSubjectDTO> subjects = new ArrayList<>();
        List<StudentOnYear> studentOnYears = studentOnYearRepository.findByStudent_User_Id(student.getUser().getId());

        for (StudentOnYear studentOnYear : studentOnYears) {
            YearOfStudy yearOfStudy = studentOnYear.getYearOfStudy();
            if (yearOfStudy == null) {
                continue;
            }

            StudyProgramme studyProgramme = yearOfStudy.getStudyProgramme();
            List<Subject> yearSubjects = yearOfStudy.getSubjects();

            if (yearSubjects != null) {
                for (Subject subject : yearSubjects) {
                    StudentSubjectDTO dto = new StudentSubjectDTO();
                    dto.setId(subject.getId());
                    dto.setName(subject.getName());
                    dto.setEcts(subject.getEcts());
                    dto.setMandatory(subject.isMandatory());
                    dto.setYearNumber(yearOfStudy.getYearNumber());

                    if (studyProgramme != null) {
                        dto.setStudyProgrammeName(studyProgramme.getName());
                    }

                    if (subject.getSubjectRealisation() != null && !subject.getSubjectRealisation().isEmpty()) {
                        SubjectRealisation firstRealisation = subject.getSubjectRealisation().get(0);
                        dto.setSubjectRealisationId(firstRealisation.getId());
                    }

                    subjects.add(dto);
                }
            }
        }

        return subjects;
    }

    public List<StudentActivityDTO> getStudentActivities(Long studentId) {
        Student student = findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        List<StudentActivityDTO> activities = new ArrayList<>();
        List<Examination> examinations = examinationRepository.findByStudentOnYear_Student_User_Id(student.getUser().getId());

        for (Examination examination : examinations) {
            KnowledgeEvaluation knowledgeEvaluation = examination.getKnowledgeEvaluation();
            if (knowledgeEvaluation == null) {
                continue;
            }

            SubjectRealisation subjectRealisation = knowledgeEvaluation.getSubjectRealisation();
            if (subjectRealisation == null || subjectRealisation.getSubject() == null) {
                continue;
            }

            StudentActivityDTO dto = new StudentActivityDTO();
            dto.setId(examination.getId());
            dto.setPoints(examination.getPoints());
            dto.setMaxPoints(knowledgeEvaluation.getPoints());
            dto.setStartTime(knowledgeEvaluation.getStartTime());
            dto.setEndTime(knowledgeEvaluation.getEndTime());
            dto.setSubjectName(subjectRealisation.getSubject().getName());
            dto.setKnowledgeEvaluationId(knowledgeEvaluation.getId());

            if (knowledgeEvaluation.getEvaluationType() != null) {
                dto.setEvaluationTypeName(knowledgeEvaluation.getEvaluationType().getName());
            }

            if (knowledgeEvaluation.getExaminationPeriod() != null) {
                dto.setExaminationPeriodName(knowledgeEvaluation.getExaminationPeriod().getName());
            }

            activities.add(dto);
        }

        return activities;
    }

    @Transactional
    public Examination registerForExam(Long studentId, Long knowledgeEvaluationId) {
        Student student = findById(studentId);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        KnowledgeEvaluation knowledgeEvaluation = knowledgeEvaluationRepository.findById(knowledgeEvaluationId)
                .orElseThrow(() -> new RuntimeException("Knowledge Evaluation not found"));

        Optional<StudentOnYear> studentOnYearOpt = studentOnYearRepository
                .findFirstByStudent_User_IdOrderByEnrolmentDateDesc(student.getUser().getId());

        if (studentOnYearOpt.isEmpty()) {
            throw new RuntimeException("Student is not enrolled in any year");
        }

        StudentOnYear studentOnYear = studentOnYearOpt.get();

        Optional<Examination> existingExam = examinationRepository
                .findByStudentOnYear_Student_User_IdAndKnowledgeEvaluation_Id(
                        student.getUser().getId(),
                        knowledgeEvaluationId);

        if (existingExam.isPresent()) {
            throw new RuntimeException("Student is already registered for this exam");
        }

        ExaminationPeriod examinationPeriod = knowledgeEvaluation.getExaminationPeriod();
        if (examinationPeriod == null || !examinationPeriod.getActive()) {
            throw new RuntimeException("Examination period is not active");
        }

        Examination examination = new Examination();
        examination.setStudentOnYear(studentOnYear);
        examination.setKnowledgeEvaluation(knowledgeEvaluation);
        examination.setPoints(0);
        examination.setNote(null);

        return examinationRepository.save(examination);
    }
}