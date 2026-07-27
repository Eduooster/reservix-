package br.com.reservix.infrastructure.web.dtos.invitation;

public record AcceptInvitationRequest(
        String name,

        String password
) {
}
