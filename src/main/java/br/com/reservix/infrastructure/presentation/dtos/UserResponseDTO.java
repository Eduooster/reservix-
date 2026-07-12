package br.com.reservix.infrastructure.presentation.dtos;

import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.domain.entities.UserRole;

public record UserResponseDTO(
        Long id, String email, String name, UserRole role, boolean active
) {
}
