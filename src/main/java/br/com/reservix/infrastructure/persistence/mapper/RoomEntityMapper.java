package br.com.reservix.infrastructure.persistence.mapper;


import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.infrastructure.persistence.entities.RoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomEntityMapper {
    Room toDomain(RoomEntity room);

   RoomEntity toEntity(Room room);
}
