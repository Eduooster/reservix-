package br.com.reservix.infrastructure.web.dtos.room;

public record CreateRoomRequest(String name, String description,Long capacity) {
}
