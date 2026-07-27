package br.com.reservix.core.application.usecases.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservationCreatedNotification(
        Long reservationId,
        String userName,
        String userEmail,
        String roomName,
        String companyName,
        LocalDate reservationDate,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String reservationUrl
) {
}
