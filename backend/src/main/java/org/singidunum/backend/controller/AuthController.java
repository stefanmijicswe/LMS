package org.singidunum.backend.controller;


import org.singidunum.backend.config.TokenGenerator;
import org.singidunum.backend.dto.JwtDTO;
import org.singidunum.backend.dto.SignInDTO;
import org.singidunum.backend.dto.SignUpDTO;
import org.singidunum.backend.model.User;
import org.singidunum.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final TokenGenerator tokenGenerator;

    public AuthController(AuthenticationManager authenticationManager,
                          AuthService authService,
                          TokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtDTO> signUp(@RequestBody SignUpDTO data) {
        User user = authService.signUp(data.getUsername(), data.getPassword());


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())
        );

        String token = tokenGenerator.generateAccessToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtDTO(token));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDTO> signIn(@RequestBody SignInDTO data) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())
        );

        User user = authService.findDomainUserByUsername(data.getUsername());

        String token = tokenGenerator.generateAccessToken(user);
        return ResponseEntity.ok(new JwtDTO(token));
    }
}

