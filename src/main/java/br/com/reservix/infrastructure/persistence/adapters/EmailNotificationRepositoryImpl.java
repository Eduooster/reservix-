package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.EmailNotificationRepository;
import br.com.reservix.core.domain.entities.EmailNotification;
import br.com.reservix.infrastructure.persistence.mapper.EmailNotificationEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaEmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailNotificationRepositoryImpl implements EmailNotificationRepository {

    private final JpaEmailNotificationRepository jpaEmailNotificationRepository;
    private final EmailNotificationEntityMapper emailNotificationEntityMapper;

    @Override
    public void save(EmailNotification emailNotification) {
        jpaEmailNotificationRepository.save(emailNotificationEntityMapper.toEntity(emailNotification));

    }
}
