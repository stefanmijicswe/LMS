package org.singidunum.backend.controller;

import org.singidunum.backend.dto.CreateStudentServiceDTO;
import org.singidunum.backend.dto.CreateTeacherDTO;
import org.singidunum.backend.dto.UserResponseDTO;
import org.singidunum.backend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/student-services")
    public ResponseEntity<UserResponseDTO> createStudentService(
            @RequestBody CreateStudentServiceDTO dto
    ) {
        UserResponseDTO createdUser = adminService.createStudentService(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/teachers")
    public ResponseEntity<UserResponseDTO> createTeacher(
            @RequestBody CreateTeacherDTO dto
    ) {
        UserResponseDTO createdUser = adminService.createTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
