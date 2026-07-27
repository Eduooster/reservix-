package br.com.reservix.infrastructure.persistence.repositories;

import br.com.reservix.infrastructure.persistence.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<ReservationEntity, Long>, JpaSpecificationExecutor<ReservationEntity> {



    @Query("""
    SELECT r
    FROM ReservationEntity r
    WHERE r.room.id = :roomId
      AND r.startDateTime <= :endOfDay
      AND r.endDateTime >= :startOfDay
    ORDER BY r.startDateTime ASC
""")
    List<ReservationEntity> findByRoomAndDate(
            @Param("roomId") Long roomId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    @Query("SELECT COUNT(r) > 0 FROM ReservationEntity r WHERE r.room.id = :id AND r.startDateTime < :end AND r.endDateTime > :start")
    boolean existsConflict(Long id, LocalDateTime start, LocalDateTime end);

    @Query("""
    SELECT r
    FROM ReservationEntity r
    WHERE r.status = 'ACTIVE'
      AND r.endDateTime <= :now
""")
    List<ReservationEntity> findExpiredReservations(@Param("now") LocalDateTime now);
}
