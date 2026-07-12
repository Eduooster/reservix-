package br.com.reservix.infrastructure.persistence.mapper.UserMapper;


import br.com.reservix.core.domain.entities.User;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toDomain (UserEntity userEntity);
    UserEntity toEntity (User user);




}
