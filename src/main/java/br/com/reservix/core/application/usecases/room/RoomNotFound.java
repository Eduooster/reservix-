package br.com.reservix.core.application.usecases.room;

public class RoomNotFound extends RuntimeException {
  public RoomNotFound(String message) {
    super(message);
  }
}
