package br.com.reservix.core.application.usecases.reservation;

import br.com.reservix.core.domain.entities.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationDetailOutPut(Long id,
                                      Long roomId,
                                      String roomName,

                                      Long userId,
                                      String userName,

                                      LocalDateTime startDateTime,
                                      LocalDateTime endDateTime,

                                      ReservationStatus reservationStatus) {
}
