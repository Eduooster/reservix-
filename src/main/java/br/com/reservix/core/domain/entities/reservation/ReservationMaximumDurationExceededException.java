package br.com.reservix.core.domain.entities.reservation;

public class ReservationMaximumDurationExceededException extends RuntimeException {
    public ReservationMaximumDurationExceededException(String message) {
        super(message);
    }
}
