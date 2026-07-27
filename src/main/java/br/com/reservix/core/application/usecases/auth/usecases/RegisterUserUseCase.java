package br.com.reservix.core.application.usecases.auth.usecases;

import br.com.reservix.core.application.ports.out.TokenServiceGateway;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.auth.RegisterUserCommand;
import br.com.reservix.core.application.usecases.auth.RegisterUserOutPut;
import br.com.reservix.core.domain.entities.User;

import br.com.reservix.core.domain.entities.UserRole;
import br.com.reservix.core.application.usecases.user.EmailAlreadyInUseException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenServiceGateway tokenServiceGateway;

    public RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenServiceGateway tokenServiceGateway) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenServiceGateway = tokenServiceGateway;
    }

    public RegisterUserOutPut execute(RegisterUserCommand registerUserCommand) {

        if (userRepository.existsByEmail(registerUserCommand.email())) {
            throw new EmailAlreadyInUseException("Email already in use");
        }

        User newUser = new User();
        newUser.setEmail(registerUserCommand.email());
        newUser.setPassword(passwordEncoder.encode(registerUserCommand.password()));
        newUser.setName(registerUserCommand.name());
        newUser.setActive(true);
        newUser.setRole(UserRole.EMPLOYEE);



        User savedUser = userRepository.save(newUser);

        String jwt = tokenServiceGateway.generateToken(savedUser.getId());


        return new RegisterUserOutPut(savedUser.getId(), savedUser.getEmail(), savedUser.getName(),savedUser.getRole(), savedUser.isActive());
    }
}
