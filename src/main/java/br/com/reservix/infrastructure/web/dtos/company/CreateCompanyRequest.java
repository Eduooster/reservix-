package br.com.reservix.infrastructure.web.dtos.company;

import java.time.LocalTime;

public record CreateCompanyRequest(
        String companyName,
        String cnpj,


        String adminName,
        String adminEmail,
        String adminPassword,
        LocalTime workDayStart,
        LocalTime workDayEnd
) {
}
