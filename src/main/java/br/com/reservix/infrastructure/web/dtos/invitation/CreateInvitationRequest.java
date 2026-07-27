package br.com.reservix.infrastructure.web.dtos.invitation;

import br.com.reservix.core.domain.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateInvitationRequest(
        @NotBlank
        @Email
        String email,

        @NotNull
        UserRole role
) {
}
