package br.com.reservix.core.application.usecases.invitation;

import br.com.reservix.core.domain.entities.UserRole;

public record CreateInvitationCommand(String email, UserRole role) {
}
