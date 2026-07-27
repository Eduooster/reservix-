package br.com.reservix.core.application.usecases.auth.usecases;

import br.com.reservix.infrastructure.web.dtos.auth.UserResponse;

public class GetAuthenticatedUserUseCase {
    public UserResponse execute() {
        return new UserResponse(null,null,null,null,false);
    }
}
