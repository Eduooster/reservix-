package br.com.reservix.infrastructure.config;

import br.com.reservix.core.application.ports.out.PassEncoderService;
import br.com.reservix.core.application.ports.out.TokenServiceGateway;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.auth.GetAuthenticatedUserUseCase;
import br.com.reservix.core.application.usecases.auth.LoginUseCase;
import br.com.reservix.core.application.usecases.auth.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public LoginUseCase loginUseCase(PassEncoderService passEncoderService, UserRepository useRepository, TokenServiceGateway tokenServiceGateway) {
        return new LoginUseCase(passEncoderService, useRepository, tokenServiceGateway);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder,TokenServiceGateway tokenServiceGateway) {
        return new RegisterUserUseCase(
                userRepository,passwordEncoder,tokenServiceGateway

        );
    }

    @Bean
    public GetAuthenticatedUserUseCase getAuthenticatedUserUseCase() {
        return new GetAuthenticatedUserUseCase();
    }
}
