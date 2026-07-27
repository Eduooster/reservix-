package br.com.reservix.infrastructure.messaging.handler;

import br.com.reservix.core.application.ports.out.EmailNotificationRepository;
import br.com.reservix.core.application.ports.out.NotificationService;
import br.com.reservix.core.application.usecases.invitation.InvitationCreatedNotification;
import br.com.reservix.core.domain.entities.EmailNotification;
import br.com.reservix.core.events.InvitationCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InvitationCreatedNotificationHandler {
    private final NotificationService notificationService;
    private final EmailNotificationRepository emailNotificationRepository;

    @Transactional
    public void handle(InvitationCreatedEvent event) {


        InvitationCreatedNotification notification = buildNotification(event);

        String providerId = notificationService.sendInvitationCreated(notification);

        EmailNotification emailLog = EmailNotification.create(
                event.recipient(),
                "invitation-created"
        );
        emailLog.markAsSent(providerId);

        emailNotificationRepository.save(emailLog);
    }


    private InvitationCreatedNotification buildNotification(InvitationCreatedEvent event) {
        return new InvitationCreatedNotification(
                event.invitationId(),
                event.token(),
                event.companyName(),
                event.inviterName(),
                event.role(),
                event.recipient()
        );
    }
}