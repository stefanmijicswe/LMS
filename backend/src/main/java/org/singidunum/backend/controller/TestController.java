package org.singidunum.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/ping")
    public String publicPing() {
        return "public ok";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/only")
    public String adminOnly() {
        return "hello admin";
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @GetMapping("/professor/only")
    public String professorOnly() {
        return "hello professor";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student/only")
    public String studentOnly() {
        return "hello student";
    }

    @PreAuthorize("hasRole('STUDENT_SERVICE')")
    @GetMapping("/student-service/only")
    public String studentServiceOnly() {
        return "hello student service";
    }

    // Primjer kombinovanog access-a:
    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    @GetMapping("/teaching-staff")
    public String teachingStaff() {
        return "hello teaching staff";
    }
}
