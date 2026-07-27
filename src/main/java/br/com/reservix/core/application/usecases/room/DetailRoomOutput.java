package br.com.reservix.core.application.usecases.room;

import java.time.LocalDateTime;

public record DetailRoomOutput(Long id, String name, String description, Integer capacity, boolean active,LocalDateTime createdAt, LocalDateTime updatedAt) {
}
