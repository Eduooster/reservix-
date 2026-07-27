package br.com.reservix.core.application.usecases.reservation.usecases;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.usecases.reservation.CancelReservationCommand;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import br.com.reservix.core.application.usecases.reservation.ReservationNotFoundException;

public class CancelReservationUseCase {

    private final ReservationRepository reservationRepository;

    public CancelReservationUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void execute(CancelReservationCommand command) {

        Reservation reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        reservation.ensureBelongsTo(command.userId());

        reservation.cancel();

        reservationRepository.save(reservation);

    }
}
