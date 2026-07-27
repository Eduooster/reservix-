package br.com.reservix.infrastructure.persistence.repositories;

import br.com.reservix.core.domain.entities.EmailNotification;
import br.com.reservix.infrastructure.persistence.entities.EmailNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailNotificationRepository extends JpaRepository<EmailNotificationEntity, Long> {
}
