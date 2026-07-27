package br.com.reservix.core.application.usecases.reservation.usecases;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.usecases.reservation.ReservationDetailOutPut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FindReservationsByUserUseCase {

    private final ReservationRepository reservationRepository;

    public FindReservationsByUserUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Page<ReservationDetailOutPut>execute(Long user, Pageable pageable) {
        return null;

    }
}
