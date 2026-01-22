package org.singidunum.backend.controller;

import org.singidunum.backend.dto.EnrolStudentRequestDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.UserResponseDTO;
import org.singidunum.backend.model.StudentOnYear;
import org.singidunum.backend.model.User;
import org.singidunum.backend.repository.StudentOnYearRepository;
import org.singidunum.backend.service.EnrollmentService;
import org.singidunum.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/enrolment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;
    private final StudentOnYearRepository studentOnYearRepository;

    public EnrollmentController(EnrollmentService enrollmentService, UserService userService, StudentOnYearRepository studentOnYearRepository) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.studentOnYearRepository = studentOnYearRepository;
    }

    @PreAuthorize("hasRole('STUDENT_SERVICE')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithOnlyRoleUser(@RequestParam(required = false) String username) {
        try {
            List<User> users = userService.findUsersForEnrollment(username);
            List<UserResponseDTO> dtos = new ArrayList<>();
            for (User user : users) {
                UserResponseDTO dto = new UserResponseDTO();
                dto.setId(user.getId());
                dto.setUsername(user.getUsername());
                List<String> roleNames = new ArrayList<>();
                if (user.getRoles() != null) {
                    for (org.singidunum.backend.model.Role role : user.getRoles()) {
                        roleNames.add(role.getName());
                    }
                }
                dto.setRoles(roleNames);
                StudentOnYear latest = studentOnYearRepository
                        .findFirstByStudent_User_IdOrderByEnrolmentDateDesc(user.getId())
                        .orElse(null);
                if (latest != null && latest.getYearOfStudy() != null) {
                    dto.setYearNumber(latest.getYearOfStudy().getYearNumber());
                    if (latest.getYearOfStudy().getStudyProgramme() != null) {
                        dto.setStudyProgrammeName(latest.getYearOfStudy().getStudyProgramme().getName());
                    }
                }
                dtos.add(dto);
            }
            return ResponseEntity.ok(dtos);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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