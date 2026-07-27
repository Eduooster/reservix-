package br.com.reservix.core.application.usecases.reservation.usecases;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.usecases.reservation.ReservationDetailOutPut;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import br.com.reservix.core.application.usecases.reservation.ReservationNotFoundException;

public class FindReservationByIdUseCase {
    private ReservationRepository reservationRepository;

    public FindReservationByIdUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDetailOutPut execute(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException ("Reservation not found"));

        return new ReservationDetailOutPut(
                reservation.getId(),
                reservation.getRoom().getId(),
                reservation.getRoom().getName(),
                reservation.getUser().getId(),
                reservation.getUser().getName(),
                reservation.getStartDateTime(),
                reservation.getEndDateTime(),
                reservation.getStatus()
        );

    }
}
