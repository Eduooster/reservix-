package br.com.reservix.core.domain.entities.room;

public class RoomInactiveException extends RuntimeException {
    public RoomInactiveException(String message) {
        super(message);
    }
}
