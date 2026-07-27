package br.com.reservix.infrastructure.web.mapper;

import br.com.reservix.core.application.usecases.auth.AuthenticationToken;
import br.com.reservix.core.application.usecases.auth.LoginUserCommand;
import br.com.reservix.core.application.usecases.auth.RegisterUserCommand;
import br.com.reservix.core.application.usecases.auth.RegisterUserOutPut;
import br.com.reservix.infrastructure.web.dtos.auth.LoginRequest;
import br.com.reservix.infrastructure.web.dtos.auth.LoginResponse;
import br.com.reservix.infrastructure.web.dtos.auth.UserResponse;
import br.com.reservix.infrastructure.web.dtos.room.RegisterUserRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface AuthMapper {

    LoginUserCommand toLoginUserCommand(LoginRequest loginRequest);
    LoginResponse toLoginResponse(AuthenticationToken authenticationToken);
    RegisterUserCommand toRegisterUserCommand(RegisterUserRequest registerUserRequest);
    UserResponse toUserResponseDTO(RegisterUserOutPut registerUserOutPut);


}
