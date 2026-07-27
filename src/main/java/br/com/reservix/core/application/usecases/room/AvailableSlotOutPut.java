package br.com.reservix.core.application.usecases.room;

import java.time.LocalDateTime;

public record AvailableSlotOutPut(
        LocalDateTime start,
        LocalDateTime end
) {
}
