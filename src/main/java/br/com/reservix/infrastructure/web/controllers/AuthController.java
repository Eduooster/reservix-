package br.com.reservix.infrastructure.web.controllers;

import br.com.reservix.core.application.usecases.auth.*;
import br.com.reservix.core.application.usecases.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.reservix.core.application.usecases.auth.usecases.LoginUseCase;
import br.com.reservix.core.application.usecases.auth.usecases.RegisterUserUseCase;
import br.com.reservix.infrastructure.web.dtos.auth.LoginRequest;
import br.com.reservix.infrastructure.web.dtos.auth.LoginResponse;
import br.com.reservix.infrastructure.web.dtos.auth.UserResponse;
import br.com.reservix.infrastructure.web.dtos.room.RegisterUserRequest;
import br.com.reservix.infrastructure.web.mapper.AuthMapper;
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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {


        LoginUserCommand loginUserCommand = authMapper.toLoginUserCommand(loginRequest);

        AuthenticationToken token = loginUseCase.execute(loginUserCommand);

        log.info("autheticationToken: {}", token);

        LoginResponse response = authMapper.toLoginResponse(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest registerRequest) {

        RegisterUserCommand registerUserCommand = authMapper.toRegisterUserCommand(registerRequest);

        var newUser = registerUserUseCase.execute(registerUserCommand);


        var response = authMapper.toUserResponseDTO(newUser);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe() {

        var currentUser = getAuthenticatedUserUseCase.execute();


        return ResponseEntity.ok(currentUser);
    }
}
