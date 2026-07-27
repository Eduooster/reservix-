package br.com.reservix.infrastructure.exceptions;

public class NotificationDeliveryException extends RuntimeException {
    public NotificationDeliveryException(String message) {
        super(message);
    }
}
