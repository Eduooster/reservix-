package br.com.reservix.infrastructure.web.dtos.reservation;

import br.com.reservix.core.domain.entities.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse (Long id,
                                   Long roomId,
                                   String roomName,

                                   Long userId,
                                   String userName,

                                   LocalDateTime startDateTime,
                                   LocalDateTime endDateTime,

                                   ReservationStatus reservationStatus){
}
