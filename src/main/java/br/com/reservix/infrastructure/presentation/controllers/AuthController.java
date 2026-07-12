package br.com.reservix.infrastructure.presentation.controllers;

import br.com.reservix.core.application.usecases.auth.*;
import br.com.reservix.infrastructure.presentation.mapper.AuthMapper;
import br.com.reservix.infrastructure.presentation.dtos.LoginRequestDTO;
import br.com.reservix.infrastructure.presentation.dtos.LoginResponseDTO;
import br.com.reservix.infrastructure.presentation.dtos.RegisterUserRequestDTO;
import br.com.reservix.infrastructure.presentation.dtos.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);


    private final LoginUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;
    private final AuthMapper authMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {


        LoginUserCommand loginUserCommand = authMapper.toLoginUserCommand(loginRequest);

        AuthenticationToken token = loginUseCase.execute(loginUserCommand)
                .orElseThrow(fields -> new RuntimeException("Invalid email or password"));

        log.info("autheticationToken: {}", token);

        LoginResponseDTO response = authMapper.toLoginResponse(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterUserRequestDTO registerRequest) {
        log.info("RegisterUserRequestDTO: {}", registerRequest);

        RegisterUserCommand registerUserCommand = authMapper.toRegisterUserCommand(registerRequest);

        var newUser = registerUserUseCase.execute(registerUserCommand).orElseThrow(fields ->
                new RuntimeException("Invalid email or password"));
        log.info("New user: {}", newUser);


        var response = authMapper.toUserResponseDTO(newUser);
        log.info("Response: {}", response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe() {

        var currentUser = getAuthenticatedUserUseCase.execute();


        return ResponseEntity.ok(currentUser);
    }
}
