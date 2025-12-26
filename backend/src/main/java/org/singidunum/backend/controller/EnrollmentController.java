package org.singidunum.backend.controller;

import org.singidunum.backend.dto.EnrolStudentRequestDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrolment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT_SERVICE')")
    @PostMapping("/students")
    public ResponseEntity<Void> enrolStudent(@RequestBody EnrolStudentRequestDTO dto) {
        enrollmentService.enrolStudent(
                dto.getUsername(),
                dto.getYearOfStudyId(),
                dto.getName(),
                dto.getSurname(),
                dto.getPin(),
                dto.getAddressId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleEnrollmentException(RuntimeException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("already enrolled")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO(message));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}