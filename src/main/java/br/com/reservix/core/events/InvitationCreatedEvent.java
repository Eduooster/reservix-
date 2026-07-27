package br.com.reservix.core.events;

import java.util.UUID;

public record InvitationCreatedEvent(
        Long invitationId,
        UUID token,
        String companyName,
        String inviterName,
        String role,
        String recipient
) implements DomainEvent{}
