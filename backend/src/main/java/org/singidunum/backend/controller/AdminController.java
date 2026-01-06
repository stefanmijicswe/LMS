package org.singidunum.backend.controller;

import org.singidunum.backend.dto.*;
import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.Teacher;
import org.singidunum.backend.model.User;
import org.singidunum.backend.service.AdminService;
import org.singidunum.backend.service.TeacherService;
import org.singidunum.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final TeacherService teacherService;

    public AdminController(AdminService adminService, UserService userService, TeacherService teacherService) {

        this.adminService = adminService;
        this.userService = userService;
        this.teacherService = teacherService;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        Iterable<User> users = userService.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

        for (User user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setRoles(user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList()));
            userResponseDTOS.add(userResponseDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTOS);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = userOptional.get();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserDTO dto) {
        User updatedUser = userService.updateUser(id, dto.getUsername(), dto.getPassword());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(updatedUser.getId());
        userResponseDTO.setUsername(updatedUser.getUsername());
        userResponseDTO.setRoles(updatedUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/roles")
    public ResponseEntity<UserResponseDTO> updateUserRoles(
            @PathVariable Long id,
            @RequestBody UpdateUserRolesDTO dto) {
        User updatedUser = userService.updateRoles(id, dto.getRoles());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(updatedUser.getId());
        userResponseDTO.setUsername(updatedUser.getUsername());
        userResponseDTO.setRoles(updatedUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        Iterable<Teacher> teachers = teacherService.findAll();
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setSurname(teacher.getSurname());
            teacherDTO.setBiography(teacher.getBiography());
            teacherDTO.setUserId(teacher.getUser().getId());
            teacherDTOS.add(teacherDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(teacherDTOS);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserManagementException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("User not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("Username already exists") ||
                    message.contains("Role not found")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }


}
