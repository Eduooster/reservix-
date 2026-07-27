package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.domain.entities.EmailNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


public interface EmailNotificationRepository{
    void save(EmailNotification emailNotification);
}
