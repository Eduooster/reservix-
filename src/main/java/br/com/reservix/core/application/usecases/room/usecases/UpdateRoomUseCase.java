package br.com.reservix.core.application.usecases.room.usecases;

import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import br.com.reservix.core.application.usecases.room.UpdateRoomCommand;
import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.core.application.usecases.room.RoomNotFound;
import org.springframework.web.bind.annotation.RequestBody;

public class UpdateRoomUseCase {

    private final RoomRepository roomRepository;

    public UpdateRoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public DetailRoomOutput execute(Long id, @RequestBody UpdateRoomCommand command){

        Room room = roomRepository.findById(command.id()).orElseThrow(()-> new RoomNotFound("Room not found"));


        room.update(
                command.name(),
                command.description(),
                command.capacity()
        );
        return null;

    }
}
