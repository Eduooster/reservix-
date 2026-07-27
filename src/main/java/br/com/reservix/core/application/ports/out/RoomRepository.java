package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import br.com.reservix.core.domain.entities.room.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoomRepository {

    Room save(Room room);

    Boolean existsByName(String name);

    Optional<Room> findById(Long id);

    Page<DetailRoomOutput> listActiveRooms(Pageable pageable);
}
