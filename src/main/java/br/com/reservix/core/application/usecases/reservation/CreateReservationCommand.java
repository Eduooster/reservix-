package br.com.reservix.core.application.usecases.reservation;

import java.time.LocalDateTime;

public record CreateReservationCommand(Long roomId, LocalDateTime startDateTime,LocalDateTime endDateTime) {


}
