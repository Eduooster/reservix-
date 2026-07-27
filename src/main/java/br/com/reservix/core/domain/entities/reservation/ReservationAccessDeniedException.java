package br.com.reservix.core.domain.entities.reservation;

public class ReservationAccessDeniedException extends RuntimeException {
    public ReservationAccessDeniedException(String message) {
        super(message);
    }
}
