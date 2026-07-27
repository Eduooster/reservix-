package br.com.reservix.core.application.usecases.room.usecases;

import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.room.CreateRoomCommand;
import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.application.usecases.user.UserNotFoundException;

public class CreateRoomUseCase {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public CreateRoomUseCase(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;

        this.userRepository = userRepository;
    }


    public DetailRoomOutput execute(CreateRoomCommand command) {

        User user = userRepository.findById(command.getUserId())
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        Company company = user.getCompany();

        Boolean roomNameExists = roomRepository.existsByName(command.getName());

        if (roomNameExists) {
            throw new RuntimeException("Room with name " + command.getName() + " already exists");
        }

        Room newRoom = Room.create(
                command.getName(),
                command.getDescription(),
                command.getCapacity(),
                company

        );

        Room savedRoom = roomRepository.save(newRoom);
        return new DetailRoomOutput(savedRoom.getId(), savedRoom.getName(),savedRoom.getDescription(),savedRoom.getCapacity(),savedRoom.isActive(),savedRoom.getCreatedAt(),savedRoom.getUpdatedAt());

    }
}
