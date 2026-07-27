package br.com.reservix.core.application.usecases.reservation.usecases;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.usecases.reservation.FindReservationsCommand;
import br.com.reservix.core.application.usecases.reservation.ReservationDetailOutPut;
import br.com.reservix.core.application.usecases.reservation.ReservationFilter;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FindAllReservationsUseCase {
    private final ReservationRepository reservationRepository;

    public FindAllReservationsUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Page<ReservationDetailOutPut>  execute(FindReservationsCommand command, Pageable pageable){
        ReservationFilter filter = new ReservationFilter(
                command.start(),
                command.end(),
                command.roomId(),
                command.userId(),
                command.status()
        );

        Page<Reservation> reservations = reservationRepository.findAll(filter,pageable);


        return reservations.map(reservation -> new ReservationDetailOutPut(
                reservation.getId(),reservation.getRoom().getId(), reservation.getRoom().getName(), reservation.getUser().getId(),
                reservation.getUser().getName(),
                reservation.getStartDateTime(), reservation.getEndDateTime(),
                reservation.getStatus()
        ));
    }
}
