package br.com.reservix.core.application.usecases.reservation;

public record CancelReservationCommand(Long reservationId, Long userId) {
}
