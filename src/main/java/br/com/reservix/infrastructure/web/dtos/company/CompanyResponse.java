package br.com.reservix.infrastructure.web.dtos.company;

import java.time.LocalDateTime;

public record CompanyResponse(
        Long id,
        String name,
        String cnpj,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
