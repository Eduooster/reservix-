package br.com.reservix.core.events;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservationCreatedEvent(Long reservationId,
                                      String userName,
                                      String userEmail,
                                      String roomName,
                                      String companyName,
                                      LocalDateTime reservationDate,
                                      LocalDateTime startTime,
                                      LocalDateTime endTime) implements DomainEvent {
}
