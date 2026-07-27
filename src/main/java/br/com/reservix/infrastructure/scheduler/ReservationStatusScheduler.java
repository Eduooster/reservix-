package br.com.reservix.infrastructure.scheduler;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ReservationStatusScheduler {

    private final ReservationRepository reservationRepository;


    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void finishExpiredReservations() {

        List<Reservation> reservations =
                reservationRepository.findExpiredReservations(LocalDateTime.now());

        reservations.forEach(Reservation::finish);

        reservationRepository.saveAll(reservations);
    }
}
