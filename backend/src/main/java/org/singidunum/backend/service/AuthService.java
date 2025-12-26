package org.singidunum.backend.service;

import org.singidunum.backend.model.Role;
import org.singidunum.backend.model.User;
import org.singidunum.backend.repository.RoleRepository;
import org.singidunum.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(String username, String rawPassword) {

        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }


        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));


        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "ROLE_USER not found in DB."
                ));

        user.getRoles().add(userRole);


        return userRepository.save(user);
    }

    public User findDomainUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User domainUser = findDomainUserByUsername(username);


        return org.springframework.security.core.userdetails.User
                .withUsername(domainUser.getUsername())
                .password(domainUser.getPassword())
                .authorities(domainUser.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .build();
    }
}

