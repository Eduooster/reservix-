package br.com.reservix.infrastructure.web.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface EmailNotificationMapper {


}
