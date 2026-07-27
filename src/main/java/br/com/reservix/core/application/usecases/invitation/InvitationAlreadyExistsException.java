package br.com.reservix.core.application.usecases.invitation;

public class InvitationAlreadyExistsException extends RuntimeException {
    public InvitationAlreadyExistsException(String message) {
        super(message);
    }
}
