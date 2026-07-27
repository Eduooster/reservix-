package br.com.reservix.core.application.ports.out;


import br.com.reservix.core.application.usecases.invitation.InvitationCreatedNotification;
import br.com.reservix.core.application.usecases.reservation.ReservationCancelledNotification;
import br.com.reservix.core.application.usecases.reservation.ReservationCreatedNotification;
import br.com.reservix.core.events.InvitationCreatedEvent;


public interface NotificationService {
    String  sendReservationCreated(
            ReservationCreatedNotification notification
    );

    String  sendInvitationCreated(InvitationCreatedNotification notification);

    String sendReservationCancelled(ReservationCancelledNotification notification);


}
