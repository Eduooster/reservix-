package br.com.reservix.core.application.usecases.room.usecases;

import br.com.reservix.core.application.usecases.reservation.AvailabilityRoomCommand;
import br.com.reservix.core.application.usecases.room.AvailabilityOutput;
import org.springframework.data.domain.Page;

public class CheckRoomAvailabilityUseCase {

    public Page<AvailabilityOutput> execute(AvailabilityRoomCommand command) {
        return null;

    }
}
