package br.com.reservix.infrastructure.persistence.repositories;

import br.com.reservix.infrastructure.persistence.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomRepository extends JpaRepository<RoomEntity, Long> {
    Boolean existsByName(String name);
}
