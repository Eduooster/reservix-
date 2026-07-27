package br.com.reservix.core.application.usecases.room;

import java.time.LocalDate;

public record FindRoomAvailabilityCommand(
        Long roomId,
        LocalDate date

) {
}
