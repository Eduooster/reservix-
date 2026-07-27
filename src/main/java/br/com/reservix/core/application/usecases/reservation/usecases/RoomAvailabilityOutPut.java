package br.com.reservix.core.application.usecases.reservation.usecases;

import br.com.reservix.core.application.usecases.room.AvailableSlotOutPut;

import java.time.LocalDate;
import java.util.List;

public record RoomAvailabilityOutPut(
        Long roomId,
        LocalDate date,
        List<AvailableSlotOutPut> slots
) {
}
