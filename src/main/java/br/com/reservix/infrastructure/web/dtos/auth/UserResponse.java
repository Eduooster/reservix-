package br.com.reservix.infrastructure.web.dtos.auth;

import br.com.reservix.core.domain.entities.UserRole;

public record UserResponse(
        Long id, String email, String name, UserRole role, boolean active
) {
}
