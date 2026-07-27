package br.com.reservix.core.application.usecases.room.usecases;

import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListActiveRoomsUseCase {
    private RoomRepository roomRepository;


    public Page<DetailRoomOutput> execute(Pageable pageable) {

        return roomRepository.listActiveRooms(pageable);

    }
}
