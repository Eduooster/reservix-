package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.usecases.reservation.ReservationFilter;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import br.com.reservix.infrastructure.persistence.entities.ReservationEntity;
import br.com.reservix.infrastructure.persistence.mapper.ReservationEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaReservationRepository;
import br.com.reservix.infrastructure.persistence.repositories.ReservationSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository jpaRepository;
    @Qualifier("reservationEntityMapper")
    private final ReservationEntityMapper mapper;

    public boolean existsConflict(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return jpaRepository.existsConflict(id,startDateTime,endDateTime);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity entity = mapper.toEntity(reservation);
        ReservationEntity saved = jpaRepository.save(entity);

        return mapper.toDomain(saved);

    }

    @Override
    public Optional<Reservation> findById(Long aLong) {
        return jpaRepository.findById(aLong).map(mapper::toDomain);
    }

    @Override
    public Page<Reservation> findAll(ReservationFilter filter, Pageable pageable) {

        Specification<ReservationEntity> spec = ReservationSpecification.withFilter(filter);

        return jpaRepository.findAll(spec, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public List<Reservation> findReservationsByRoomAndDate(Long roomId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return jpaRepository.findByRoomAndDate(roomId, startOfDay, endOfDay).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void saveAll(List<Reservation> reservations) {
        jpaRepository.saveAll(reservations.stream().map(mapper::toEntity).collect(Collectors.toList()));

    }

    @Override
    public List<Reservation> findExpiredReservations(LocalDateTime now) {
        return jpaRepository.findExpiredReservations(now)
                .stream()
                .map(mapper::toDomain)
                .toList();

    }
}
