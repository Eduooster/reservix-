package br.com.reservix.core.application.usecases.auth;

import br.com.reservix.core.domain.entities.UserRole;

public record RegisterUserOutPut(
        Long id, String email, String name, UserRole role, boolean active
) {
}
