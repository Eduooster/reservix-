package br.com.reservix.core.application.usecases.auth;

import br.com.reservix.infrastructure.presentation.dtos.UserResponseDTO;

public class GetAuthenticatedUserUseCase {
    public UserResponseDTO execute() {
        return new UserResponseDTO(null,null,null,null,false);
    }
}
