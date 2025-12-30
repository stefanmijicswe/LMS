package org.singidunum.backend.controller;

import org.singidunum.backend.dto.CreateTeacherInCourseDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.TeacherInCourseDTO;
import org.singidunum.backend.dto.UpdateTeacherInCourseDTO;
import org.singidunum.backend.model.TeacherInCourse;
import org.singidunum.backend.service.TeacherInCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/teacher-in-courses")
@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT_SERVICE')")
public class TeacherInCourseController {

    private final TeacherInCourseService teacherInCourseService;

    public TeacherInCourseController(TeacherInCourseService teacherInCourseService) {
        this.teacherInCourseService = teacherInCourseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherInCourseDTO> getTeacherInCourseById(@PathVariable Long id) {
        TeacherInCourse teacherInCourse = teacherInCourseService.findById(id);

        if (teacherInCourse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        TeacherInCourseDTO teacherInCourseDTO = new TeacherInCourseDTO();
        teacherInCourseDTO.setId(teacherInCourse.getId());
        teacherInCourseDTO.setNumberOfClasses(teacherInCourse.getNumberOfClasses());

        if (teacherInCourse.getTeacher() != null) {
            teacherInCourseDTO.setTeacherId(teacherInCourse.getTeacher().getId());
        }

        if (teacherInCourse.getSubjectRealisation() != null) {
            teacherInCourseDTO.setSubjectRealisationId(teacherInCourse.getSubjectRealisation().getId());
        }

        if (teacherInCourse.getTeachingType() != null) {
            teacherInCourseDTO.setTeachingTypeId(teacherInCourse.getTeachingType().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(teacherInCourseDTO);
    }

    @PostMapping
    public ResponseEntity<TeacherInCourseDTO> createTeacherInCourse(
            @RequestBody CreateTeacherInCourseDTO dto) {

        TeacherInCourse createdTeacherInCourse = teacherInCourseService.create(
                dto.getTeacherId(),
                dto.getSubjectRealisationId(),
                dto.getTeachingTypeId(),
                dto.getNumberOfClasses()
        );

        TeacherInCourseDTO teacherInCourseDTO = new TeacherInCourseDTO();
        teacherInCourseDTO.setId(createdTeacherInCourse.getId());
        teacherInCourseDTO.setNumberOfClasses(createdTeacherInCourse.getNumberOfClasses());

        if (createdTeacherInCourse.getTeacher() != null) {
            teacherInCourseDTO.setTeacherId(createdTeacherInCourse.getTeacher().getId());
        }

        if (createdTeacherInCourse.getSubjectRealisation() != null) {
            teacherInCourseDTO.setSubjectRealisationId(createdTeacherInCourse.getSubjectRealisation().getId());
        }

        if (createdTeacherInCourse.getTeachingType() != null) {
            teacherInCourseDTO.setTeachingTypeId(createdTeacherInCourse.getTeachingType().getId());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherInCourseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherInCourseDTO> updateTeacherInCourse(
            @PathVariable Long id,
            @RequestBody UpdateTeacherInCourseDTO dto) {

        TeacherInCourse updatedTeacherInCourse = teacherInCourseService.update(
                id,
                dto.getTeacherId(),
                dto.getSubjectRealisationId(),
                dto.getTeachingTypeId(),
                dto.getNumberOfClasses()
        );

        TeacherInCourseDTO teacherInCourseDTO = new TeacherInCourseDTO();
        teacherInCourseDTO.setId(updatedTeacherInCourse.getId());
        teacherInCourseDTO.setNumberOfClasses(updatedTeacherInCourse.getNumberOfClasses());

        if (updatedTeacherInCourse.getTeacher() != null) {
            teacherInCourseDTO.setTeacherId(updatedTeacherInCourse.getTeacher().getId());
        }

        if (updatedTeacherInCourse.getSubjectRealisation() != null) {
            teacherInCourseDTO.setSubjectRealisationId(updatedTeacherInCourse.getSubjectRealisation().getId());
        }

        if (updatedTeacherInCourse.getTeachingType() != null) {
            teacherInCourseDTO.setTeachingTypeId(updatedTeacherInCourse.getTeachingType().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(teacherInCourseDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleTeacherInCourseException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("TeacherInCourse not found") ||
                    message.contains("Teacher not found") ||
                    message.contains("Subject Realisation not found") ||
                    message.contains("Teaching Type not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("Teacher ID is required") ||
                    message.contains("Subject Realisation ID is required") ||
                    message.contains("Teaching Type ID is required") ||
                    message.contains("Number of classes is required")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}