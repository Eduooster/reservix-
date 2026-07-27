package br.com.reservix.core.domain.entities.reservation;

public class ReservationInPastException extends RuntimeException {
    public ReservationInPastException(String message) {
        super(message);
    }
}
