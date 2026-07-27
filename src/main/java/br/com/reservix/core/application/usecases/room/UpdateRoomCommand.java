package br.com.reservix.core.application.usecases.room;

public record UpdateRoomCommand(Long id, String name, String description, Integer capacity, boolean active
) {
}
