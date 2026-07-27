package br.com.reservix.core.events;

import java.time.LocalDateTime;

public record ReservationCancelledEvent(
        Long reservationId,

        String userName,
        String userEmail,

        String roomName,
        String companyName,

        LocalDateTime reservationDate,

        LocalDateTime startTime,
        LocalDateTime endTime,

        String cancelledBy,

        String cancellationReason
) {
}
