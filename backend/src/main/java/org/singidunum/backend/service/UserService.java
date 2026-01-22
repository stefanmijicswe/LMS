package org.singidunum.backend.service;

import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.User;
import org.singidunum.backend.repository.RoleRepository;
import org.singidunum.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                      RoleRepository roleRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Iterable<User> findAll() {
        return userRepository.findByActiveTrue();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findByIdAndActiveTrue(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, String username, String password) {
        User user = userRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (username != null && !username.equals(user.getUsername())) {
            if (userRepository.existsByUsername(username)) {
                throw new RuntimeException("Username already exists: " + username);
            }
            user.setUsername(username);
        }

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        return userRepository.save(user);
    }

    @Transactional
    public User updateRoles(Long userId, List<String> roleNames) {
        User user = userRepository.findByIdAndActiveTrue(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersWithOnlyRoleUser(String usernameFilter) {
        List<User> users;
        if (usernameFilter != null && !usernameFilter.trim().isEmpty()) {
            users = userRepository.findByActiveTrueAndUsernameContainingIgnoreCase(usernameFilter.trim());
        } else {
            users = userRepository.findByActiveTrue();
        }

        Optional<Role> roleUserOpt = roleRepository.findByName("ROLE_USER");
        if (roleUserOpt.isEmpty()) {
            return new ArrayList<>();
        }

        Role roleUser = roleUserOpt.get();
        List<User> result = new ArrayList<>();

        for (User user : users) {
            if (user.getRoles() != null && user.getRoles().size() == 1) {
                Role userRole = user.getRoles().get(0);
                if (userRole != null && userRole.getName() != null && userRole.getName().equals("ROLE_USER")) {
                    result.add(user);
                }
            }
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<User> findUsersForEnrollment(String usernameFilter) {
        List<User> users;
        if (usernameFilter != null && !usernameFilter.trim().isEmpty()) {
            users = userRepository.findByActiveTrueAndUsernameContainingIgnoreCase(usernameFilter.trim());
        } else {
            users = userRepository.findByActiveTrue();
        }

        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getRoles() == null) {
                continue;
            }
            boolean hasRoleUser = user.getRoles().stream()
                    .anyMatch(role -> role != null && "ROLE_USER".equals(role.getName()));
            boolean hasRoleStudent = user.getRoles().stream()
                    .anyMatch(role -> role != null && "ROLE_STUDENT".equals(role.getName()));
            if (hasRoleUser || hasRoleStudent) {
                result.add(user);
            }
        }

        return result;
    }
}
