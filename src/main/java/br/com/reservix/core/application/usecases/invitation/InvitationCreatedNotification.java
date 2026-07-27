package br.com.reservix.core.application.usecases.invitation;

import java.util.UUID;

public record InvitationCreatedNotification(
        Long invitationId,
        UUID token,
        String comapanyName,
        String inviterName,
        String role,
        String recipient
) {
}
