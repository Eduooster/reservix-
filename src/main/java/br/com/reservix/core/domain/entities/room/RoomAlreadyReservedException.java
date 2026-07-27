package br.com.reservix.core.domain.entities.room;

public class RoomAlreadyReservedException extends RuntimeException {
    public RoomAlreadyReservedException(String message) {
        super(message);
    }
}
