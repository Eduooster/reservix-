package br.com.reservix.core.domain.entities.room;


import br.com.reservix.core.domain.entities.company.Company;

import java.time.LocalDateTime;

public class Room {
    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Company company;

    public Room() {
    }

    public Room(Long id, String name, String description, Integer capacity, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, Company company) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.company = company;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static Room create(String name, String description, int capacity,Company company) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacidade inválida");
        Room room = new Room();
        room.setName(name);
        room.setDescription(description);
        room.setCapacity(capacity);
        room.setCreatedAt(LocalDateTime.now());
        room.setUpdatedAt(LocalDateTime.now());
        room.setActive(true);
        room.setCompany(company);
        return room;
    }

    public void update(String name, String description, Integer capacity) {
        if (name != null) {
            this.name = name;
        }

        if (description != null) {
            this.description = description;
        }

        if (capacity != null) {
            this.capacity = capacity;
        }
    }

    public void ensureIsAvailableForReservation() {

        if (!this.isActive()) {
            throw new RoomInactiveException("Inactive room");
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", company=" + company +
                '}';
    }
}
