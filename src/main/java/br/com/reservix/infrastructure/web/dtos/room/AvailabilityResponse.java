package br.com.reservix.infrastructure.web.dtos.room;

import br.com.reservix.core.application.usecases.room.AvailableSlotOutPut;

import java.time.LocalDate;
import java.util.List;

public record AvailabilityResponse(
        Long roomId,
        LocalDate date,
        List<AvailableSlotOutPut> slots
) {
}
