package br.com.reservix.core.application.usecases.room;

public class CreateRoomCommand {

    private Long userId;
    private String name;
    private String description;
    private Integer capacity;


    public CreateRoomCommand(Long userId,String name, String description, Integer capacity) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}