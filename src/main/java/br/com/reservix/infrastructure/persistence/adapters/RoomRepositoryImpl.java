package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.infrastructure.persistence.mapper.RoomEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor

public class RoomRepositoryImpl implements RoomRepository {

    private final JpaRoomRepository roomRepository;
    private final RoomEntityMapper roomEntityMapper;


    @Override
    public Room save(Room room) {
        return roomEntityMapper.toDomain(roomRepository.save(roomEntityMapper.toEntity(room)));
    }

    @Override
    public Boolean existsByName(String name) {
        return roomRepository.existsByName(name);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id).map(
                roomEntity -> roomEntityMapper.toDomain(roomEntity)
        );
    }

    @Override
    public Page<DetailRoomOutput> listActiveRooms(Pageable pageable) {
        return roomRepository.findAll(pageable).map(
                roomEntity -> new DetailRoomOutput(
                        roomEntity.getId(),roomEntity.getName(),roomEntity.getDescription(),roomEntity.getCapacity(),roomEntity.isActive(),roomEntity.getCreatedAt(),roomEntity.getUpdatedAt()
                )
        );
    }



}
