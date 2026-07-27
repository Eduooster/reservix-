package br.com.reservix.core.application.usecases.room.usecases;

import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.usecases.room.AvailableSlotOutPut;
import br.com.reservix.core.application.usecases.room.FindRoomAvailabilityCommand;
import br.com.reservix.core.application.usecases.reservation.usecases.RoomAvailabilityOutPut;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.core.application.usecases.room.RoomNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FindRoomAvaliabilityUseCase {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public FindRoomAvaliabilityUseCase(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public RoomAvailabilityOutPut execute(FindRoomAvailabilityCommand command) {

        Room room = roomRepository.findById(command.roomId())
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        log.info("Room found: " + room.toString());



        room.ensureIsAvailableForReservation();

        List<Reservation> reservations =
                reservationRepository.findReservationsByRoomAndDate(
                        room.getId(),
                        command.date()
                );

        List<AvailableSlotOutPut> slots =
                calculateAvailableSlots(command.date(), reservations,room.getCompany().getWorkdayStart(),room.getCompany().getWorkdayEnd());

        return new RoomAvailabilityOutPut(
                room.getId(),
                command.date(),
                slots
        );
    }

    private List<AvailableSlotOutPut> calculateAvailableSlots(
            LocalDate date,
            List<Reservation> reservations,
            LocalTime workDayStart,
            LocalTime workDayEnd) {

        List<AvailableSlotOutPut> availableSlots = new ArrayList<>();

        LocalDateTime current = date.atTime(workDayStart);

        LocalDateTime endOfWorkDay = date.atTime(workDayEnd);

        for (Reservation reservation : reservations) {

            if (current.isBefore(reservation.getStartDateTime())) {

                availableSlots.add(
                        new AvailableSlotOutPut(
                                current,
                                reservation.getStartDateTime()
                        )
                );
            }

            if (reservation.getEndDateTime().isAfter(current)) {
                current = reservation.getEndDateTime();
            }
        }

        if (current.isBefore(endOfWorkDay)) {

            availableSlots.add(
                    new AvailableSlotOutPut(
                            current,
                            endOfWorkDay
                    )
            );
        }

        return availableSlots;
    }


}

