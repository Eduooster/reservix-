package br.com.reservix.core.application.usecases.reservation.usecases;

import br.com.reservix.core.application.ports.out.DomainEventPublisher;
import br.com.reservix.core.application.ports.out.ReservationRepository;
import br.com.reservix.core.application.ports.out.RoomRepository;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.reservation.CreateReservationCommand;
import br.com.reservix.core.application.usecases.reservation.ReservationDetailOutPut;
import br.com.reservix.core.domain.entities.reservation.Reservation;
import br.com.reservix.core.domain.entities.room.Room;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.domain.entities.room.RoomAlreadyReservedException;
import br.com.reservix.core.application.usecases.room.RoomNotFoundException;
import br.com.reservix.core.application.usecases.user.UserNotFoundException;

import br.com.reservix.core.events.ReservationCreatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;

    public CreateReservationUseCase(ReservationRepository reservationRepository, RoomRepository roomRepository, UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public ReservationDetailOutPut execute(CreateReservationCommand command, Long userId) {


        Room room = roomRepository.findById(command.roomId())
                .orElseThrow(()-> new RoomNotFoundException("Room not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        room.ensureIsAvailableForReservation();

        Reservation.ensureValidPeriod(command.startDateTime(),command.endDateTime());
        Reservation.validateMaximumAdvance(command.startDateTime());
        Reservation.validateMaximumDuration(command.startDateTime(),command.endDateTime());

        Reservation reservation = Reservation.create(
                room,
                user,
                command.startDateTime(),
                command.endDateTime()
        );





        boolean hasConflict = reservationRepository.existsConflict(
                room.getId(),
                command.startDateTime(),
                command.endDateTime()
        );

        if (hasConflict) {
            throw new RoomAlreadyReservedException("Room already reserved");
        }

        Reservation saved = reservationRepository.save(reservation);

        domainEventPublisher.publish(
               new ReservationCreatedEvent(
                       saved.getId(),
                       user.getName(),
                       user.getEmail(),
                       saved.getRoom().getName(),
                       user.getCompany().getName(),
                       saved.getCreatedAt(),
                       saved.getStartDateTime(),
                       saved.getEndDateTime())
        );



        return new ReservationDetailOutPut(
                saved.getId(),
                saved.getRoom().getId(),
                saved.getRoom().getName(),
                saved.getUser().getId(),
                saved.getUser().getName(),
                reservation.getStartDateTime(),
                reservation.getEndDateTime(),
                saved.getStatus()

        );
    }
}
