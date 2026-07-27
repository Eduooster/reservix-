package br.com.reservix.core.application.usecases.reservation;

import br.com.reservix.core.domain.entities.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationFilter(

        LocalDateTime start,
        LocalDateTime end,
        Long roomId,
        Long userId,
        ReservationStatus status

) {
}