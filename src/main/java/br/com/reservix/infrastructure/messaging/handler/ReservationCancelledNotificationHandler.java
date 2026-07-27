package br.com.reservix.infrastructure.messaging.handler;

import br.com.reservix.core.application.ports.out.EmailNotificationRepository;
import br.com.reservix.core.application.ports.out.NotificationService;
import br.com.reservix.core.application.usecases.reservation.ReservationCancelledNotification;
import br.com.reservix.core.application.usecases.reservation.ReservationCreatedNotification;
import br.com.reservix.core.domain.entities.EmailNotification;
import br.com.reservix.core.events.ReservationCancelledEvent;
import br.com.reservix.core.events.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class ReservationCancelledNotificationHandler{
    private final NotificationService notificationService;
    private final EmailNotificationRepository emailNotificationRepository;


    public void handle(ReservationCancelledEvent event) {

        ReservationCancelledNotification reservationCreatedNotification = buildNotification(event);

        String providerId = notificationService.sendReservationCancelled(reservationCreatedNotification);

        EmailNotification emailLog = EmailNotification.create(
                event.userEmail(),
                "invitation-created"
        );
        emailLog.markAsSent(providerId);

        emailNotificationRepository.save(emailLog);


    }

    private ReservationCancelledNotification buildNotification(ReservationCancelledEvent event) {
        return new ReservationCancelledNotification(
                event.userName(),
                event.roomName(),
                event.companyName(),
                event.userEmail(),
                event.reservationDate(),
                event.startTime(),
                event.endTime(),
                "2026"

        );
    }
}
