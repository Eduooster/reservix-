package br.com.reservix.core.application.usecases.company;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateCompanyCommand(
        String companyName,
        String cnpj,

        String adminName,
        String adminEmail,
        String adminPassword,
        LocalTime workDayStart,
        LocalTime workDayEnd
) {
}
