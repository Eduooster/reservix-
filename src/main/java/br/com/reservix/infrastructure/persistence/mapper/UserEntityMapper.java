package br.com.reservix.infrastructure.persistence.mapper;


import br.com.reservix.core.domain.entities.User;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import br.com.reservix.infrastructure.web.mapper.CompanyMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = CompanyMapper.class)
public interface UserEntityMapper {
    User toDomain (UserEntity userEntity);
    UserEntity toEntity (User user);




}
