package br.com.reservix.infrastructure.web.dtos.room;

import java.time.LocalDateTime;

public record RoomResponse(Long id, String name, String description, Integer capacity, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
