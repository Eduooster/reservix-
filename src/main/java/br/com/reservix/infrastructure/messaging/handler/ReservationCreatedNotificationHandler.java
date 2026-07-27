package br.com.reservix.infrastructure.messaging.handler;

import br.com.reservix.core.application.ports.out.EmailNotificationRepository;
import br.com.reservix.core.application.ports.out.NotificationService;

import br.com.reservix.core.application.usecases.invitation.InvitationCreatedNotification;
import br.com.reservix.core.application.usecases.reservation.ReservationCreatedNotification;

import br.com.reservix.core.domain.entities.EmailNotification;
import br.com.reservix.core.events.InvitationCreatedEvent;
import br.com.reservix.core.events.ReservationCreatedEvent;
import br.com.reservix.infrastructure.persistence.adapters.EmailNotificationRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReservationCreatedNotificationHandler {

    private final NotificationService notificationService;
    private final EmailNotificationRepository emailNotificationRepository;




    @Transactional
    public void handle(ReservationCreatedEvent event) {

        ReservationCreatedNotification notification = buildNotification(event);

        String providerId =  notificationService.sendReservationCreated(notification);

        EmailNotification emailLog = EmailNotification.create(
                event.userEmail(),
                "reservation-created"
        );
        emailLog.markAsSent(providerId);



        notificationService.sendReservationCreated(notification);
    }

    private ReservationCreatedNotification buildNotification(ReservationCreatedEvent event) {
        return new ReservationCreatedNotification(
                event.reservationId(),
                event.userName(),
                event.userEmail(),
                event.roomName(),
                event.companyName(),
                event.reservationDate().toLocalDate(),
                event.startTime(),
                event.endTime(),
                "sem url"
        );
    }
}