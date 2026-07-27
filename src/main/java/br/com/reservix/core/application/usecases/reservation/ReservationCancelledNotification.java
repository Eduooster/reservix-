package br.com.reservix.core.application.usecases.reservation;

import java.time.LocalDateTime;

public record ReservationCancelledNotification(
        String userName,
        String userEmail,
        String roomName,
        String companyName,
        LocalDateTime reservationDate,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String year
) {
}
