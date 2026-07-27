package br.com.reservix.core.application.usecases.invitation;

public class InvitationNotFoundException extends RuntimeException {
  public InvitationNotFoundException(String message) {
    super(message);
  }
}
