package br.com.reservix.infrastructure.web.dtos.reservation;

import java.time.LocalDateTime;

public record CreateReservationRequest(Long roomId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
}
