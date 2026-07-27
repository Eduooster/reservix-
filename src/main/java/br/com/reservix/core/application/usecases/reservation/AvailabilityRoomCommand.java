package br.com.reservix.core.application.usecases.reservation;

import java.time.LocalDateTime;

public record AvailabilityRoomCommand(Long id, LocalDateTime startDate, LocalDateTime endDate) {
}
