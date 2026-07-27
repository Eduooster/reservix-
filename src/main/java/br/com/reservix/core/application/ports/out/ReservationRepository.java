package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.application.usecases.reservation.ReservationFilter;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    boolean existsConflict(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime);

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(Long aLong);

    Page<Reservation> findAll(ReservationFilter filter, Pageable pageable);

    List<Reservation> findReservationsByRoomAndDate(
            Long roomId,
            LocalDate date
    );

    void saveAll(List<Reservation> reservations);

    List<Reservation> findExpiredReservations(LocalDateTime now);
}
