package br.com.reservix.infrastructure.persistence.mapper;

import br.com.reservix.core.domain.entities.reservation.Reservation;
import br.com.reservix.infrastructure.persistence.entities.ReservationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationEntityMapper {
    Reservation toDomain(ReservationEntity entity);
    ReservationEntity toEntity(Reservation domain);
}
