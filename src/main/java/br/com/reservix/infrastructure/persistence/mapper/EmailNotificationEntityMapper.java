package br.com.reservix.infrastructure.persistence.mapper;


import br.com.reservix.core.domain.entities.EmailNotification;
import br.com.reservix.infrastructure.persistence.entities.EmailNotificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailNotificationEntityMapper {

    EmailNotification toDomain(EmailNotificationEntity entity);
    EmailNotificationEntity toEntity(EmailNotification domain);
}
