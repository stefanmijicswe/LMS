package org.singidunum.backend.service;

import org.singidunum.backend.dto.CreateStudentServiceDTO;
import org.singidunum.backend.dto.CreateTeacherDTO;
import org.singidunum.backend.dto.UserResponseDTO;
import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.Teacher;
import org.singidunum.backend.model.User;
import org.singidunum.backend.repository.RoleRepository;
import org.singidunum.backend.repository.TeacherRepository;
import org.singidunum.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository,
                        RoleRepository roleRepository,
                        TeacherRepository teacherRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO createStudentService(CreateStudentServiceDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists: " + dto.getUsername());
        }

        Role roleStudentService = roleRepository.findByName("ROLE_STUDENT_SERVICE")
                .orElseThrow(() -> new RuntimeException("ROLE_STUDENT_SERVICE not found"));

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setActive(true);
        u.setRoles(List.of(roleUser, roleStudentService));

        User saved = userRepository.save(u);

        return new UserResponseDTO(
                saved.getId(),
                saved.getUsername(),
                saved.getRoles().stream().map(Role::getName).toList()
        );
    }

    @Transactional
    public UserResponseDTO createTeacher(CreateTeacherDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists: " + dto.getUsername());
        }

        if (dto.getPin() == null || dto.getPin().length() != 13) {
            throw new RuntimeException("PIN must be exactly 13 characters");
        }

        if (teacherRepository.existsByPin(dto.getPin())) {
            throw new RuntimeException("PIN already exists: " + dto.getPin());
        }

        Role roleProfessor = roleRepository.findByName("ROLE_PROFESSOR")
                .orElseThrow(() -> new RuntimeException("ROLE_PROFESSOR not found"));

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));


        User u = new User();
        u.setUsername(dto.getUsername());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setActive(true);
        u.setRoles(List.of(roleUser, roleProfessor));

        User savedUser = userRepository.save(u);


        Teacher t = new Teacher();
        t.setUser(savedUser);
        t.setPin(dto.getPin());
        t.setBiography(dto.getBiography());
        t.setName(dto.getName());
        t.setSurname(dto.getSurname());

        teacherRepository.save(t);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRoles().stream().map(Role::getName).toList()
        );
    }
}
