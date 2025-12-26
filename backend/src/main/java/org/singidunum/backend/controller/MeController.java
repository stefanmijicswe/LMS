package org.singidunum.backend.controller;

import org.singidunum.backend.dto.MeDTO;
import org.singidunum.backend.dto.TeacherMeDTO;
import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.User;
import org.singidunum.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/me")
public class MeController {

    private final AuthService authService;

    public MeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<MeDTO> me(Authentication authentication) {

        String username = authentication.getName();
        User user = authService.findDomainUserByUsername(username);

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        MeDTO dto = new MeDTO(user.getId(), user.getUsername(), roles);

        if (roles.contains("ROLE_PROFESSOR") && user.getTeacher() != null) {

            var t = user.getTeacher();

            dto.setTeacher(
                    new TeacherMeDTO(
                            t.getId(),
                            t.getName(),
                            t.getSurname(),
                            t.getBiography(),
                            t.getPin()
                    )
            );
        }

        return ResponseEntity.ok(dto);
    }
}
