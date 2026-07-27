package br.com.reservix.core.domain.entities.reservation;

public class ReservationAlreadyCancelledException extends RuntimeException {
  public ReservationAlreadyCancelledException(String message) {
    super(message);
  }
}
