package br.com.reservix.core.application.usecases.room.usecases;

import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.core.application.usecases.room.RoomNotFound;

public class GetRoomDetailsUseCase {

    private RoomRepository roomRepository;

    public GetRoomDetailsUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public DetailRoomOutput execute(Long id) {

        Room room = roomRepository.findById(id).orElseThrow(
                () -> new RoomNotFound("Room not found")
        );

        DetailRoomOutput detailRoomOutput = new DetailRoomOutput(
                room.getId(),
                room.getName(),
                room.getDescription(),
                room.getCapacity(),
                room.isActive(),
                room.getCreatedAt(),
                room.getUpdatedAt()

        );
        return detailRoomOutput;

    }
}
