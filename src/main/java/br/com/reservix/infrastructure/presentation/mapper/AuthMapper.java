package br.com.reservix.infrastructure.presentation.mapper;

import br.com.reservix.core.application.usecases.auth.AuthenticationToken;
import br.com.reservix.core.application.usecases.auth.LoginUserCommand;
import br.com.reservix.core.application.usecases.auth.RegisterUserCommand;
import br.com.reservix.core.application.usecases.auth.RegisterUserOutPut;
import br.com.reservix.infrastructure.presentation.dtos.LoginRequestDTO;
import br.com.reservix.infrastructure.presentation.dtos.LoginResponseDTO;
import br.com.reservix.infrastructure.presentation.dtos.RegisterUserRequestDTO;
import br.com.reservix.infrastructure.presentation.dtos.UserResponseDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface AuthMapper {

    LoginUserCommand toLoginUserCommand(LoginRequestDTO loginRequestDTO);
    LoginResponseDTO toLoginResponse(AuthenticationToken authenticationToken);
    RegisterUserCommand toRegisterUserCommand(RegisterUserRequestDTO registerUserRequestDTO);
    UserResponseDTO toUserResponseDTO(RegisterUserOutPut registerUserOutPut);


}
