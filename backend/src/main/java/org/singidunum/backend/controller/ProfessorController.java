package org.singidunum.backend.controller;

import org.singidunum.backend.dto.*;
import org.singidunum.backend.model.*;
import org.singidunum.backend.service.AuthService;
import org.singidunum.backend.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private final ProfessorService professorService;
    private final AuthService authService;

    public ProfessorController(ProfessorService professorService, AuthService authService) {
        this.professorService = professorService;
        this.authService = authService;
    }

    @GetMapping("/me/courses")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<List<ProfessorCourseDTO>> getMyCourses(Authentication authentication) {
        try {
            String username = authentication.getName();
            Long userId = authService.findDomainUserByUsername(username).getId();
            Teacher teacher = professorService.findByUserId(userId);

            List<ProfessorCourseDTO> courses = professorService.getProfessorCourses(teacher.getId());
            return ResponseEntity.ok(courses);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/courses/{subjectRealisationId}/students")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<List<CourseStudentDTO>> getCourseStudents(
            @PathVariable Long subjectRealisationId,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            Long userId = authService.findDomainUserByUsername(username).getId();
            Teacher teacher = professorService.findByUserId(userId);

            List<CourseStudentDTO> students = professorService.getCourseStudents(subjectRealisationId);
            return ResponseEntity.ok(students);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/examinations/{examinationId}/grade")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<ExaminationDTO> gradeStudent(
            @PathVariable Long examinationId,
            @RequestBody GradeStudentDTO dto,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            Long userId = authService.findDomainUserByUsername(username).getId();
            Teacher teacher = professorService.findByUserId(userId);

            Examination examination = professorService.gradeStudent(examinationId, dto.getPoints(), dto.getNote(), teacher.getId());

            ExaminationDTO examinationDTO = new ExaminationDTO();
            examinationDTO.setId(examination.getId());
            examinationDTO.setPoints(examination.getPoints());
            examinationDTO.setNote(examination.getNote());
            examinationDTO.setStudentOnYearId(examination.getStudentOnYear().getId());
            examinationDTO.setKnowledgeEvaluationId(examination.getKnowledgeEvaluation().getId());

            return ResponseEntity.ok(examinationDTO);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleProfessorException(RuntimeException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(message));
        }
        if (message != null && message.contains("cannot be negative")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO(message));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}