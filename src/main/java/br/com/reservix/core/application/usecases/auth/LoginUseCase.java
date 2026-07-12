package br.com.reservix.core.application.usecases.auth;

import br.com.reservix.core.application.result.Result;
import br.com.reservix.core.application.ports.out.PassEncoderService;
import br.com.reservix.core.application.ports.out.TokenServiceGateway;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.infrastructure.presentation.controllers.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginUseCase {

    private final PassEncoderService passEncoderService;
    private final UserRepository userRepository;
    private final TokenServiceGateway tokenServiceGateway;
    private static final Logger log = LoggerFactory.getLogger(LoginUseCase.class);

    public LoginUseCase(PassEncoderService passEncoderService, UserRepository userRepository, TokenServiceGateway tokenServiceGateway) {
        this.passEncoderService = passEncoderService;
        this.userRepository = userRepository;
        this.tokenServiceGateway = tokenServiceGateway;
    }


    public Result<AuthenticationToken> execute(LoginUserCommand command) {

        var user = userRepository.findByEmail(command.email()).orElse(null);

        if (user == null || !passEncoderService.matches(command.password(), user.getPassword())) {

            return Result.failure("Invalid email or password");
        }

        String token = tokenServiceGateway.generateToken(user.getId());

        log.info("Generated token: {}", token);
        return Result.success(new AuthenticationToken(token, tokenServiceGateway.expiryTime()));
    }
}
