package org.singidunum.backend.controller;

import org.singidunum.backend.dto.MeDTO;
import org.singidunum.backend.dto.TeacherMeDTO;
import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.User;
import org.singidunum.backend.service.AuthService;
import org.singidunum.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/me")
public class MeController {

    private final AuthService authService;
    private final UserService userService;

    public MeController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
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

    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody Map<String, String> body, Authentication authentication) {
        String username = authentication.getName();
        User user = authService.findDomainUserByUsername(username);
        String newPassword = body.get("newPassword");
        userService.updateUser(user.getId(), null, newPassword);
        return ResponseEntity.ok().build();
    }
}
