package br.com.reservix.core.application.usecases.company;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CompanyDetailOutPut(
        Long id,
        String name,
        String cnpj,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalTime workdayStart,
        LocalTime workdayEnd
) {
}
