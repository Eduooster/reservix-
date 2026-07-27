package br.com.reservix.core.domain.entities.reservation;

public class ReservationMaximumAdvanceExceededException extends RuntimeException {
    public ReservationMaximumAdvanceExceededException(String message) {
        super(message);
    }
}
