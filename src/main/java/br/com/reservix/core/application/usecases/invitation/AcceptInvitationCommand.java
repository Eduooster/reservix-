package br.com.reservix.core.application.usecases.invitation;

import java.util.UUID;

public record AcceptInvitationCommand(UUID token,

                                      String name,

                                      String password) {
}
