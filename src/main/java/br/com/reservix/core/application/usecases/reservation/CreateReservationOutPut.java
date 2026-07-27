package br.com.reservix.core.application.usecases.reservation;

import br.com.reservix.core.domain.entities.ReservationStatus;

public record CreateReservationOutPut(Long id, Long roomId, Long userId, ReservationStatus reservationStatus) {
}
