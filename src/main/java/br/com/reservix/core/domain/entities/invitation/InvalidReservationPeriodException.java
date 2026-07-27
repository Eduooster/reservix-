package br.com.reservix.core.domain.entities.invitation;

public class InvalidReservationPeriodException extends RuntimeException {
  public InvalidReservationPeriodException(String message) {
    super(message);
  }
}
