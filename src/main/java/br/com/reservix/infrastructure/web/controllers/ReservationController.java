package br.com.reservix.infrastructure.web.controllers;

import br.com.reservix.core.application.usecases.reservation.*;
import br.com.reservix.core.application.usecases.reservation.usecases.*;
import br.com.reservix.core.domain.entities.ReservationStatus;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import br.com.reservix.infrastructure.web.dtos.reservation.CreateReservationRequest;
import br.com.reservix.infrastructure.web.dtos.reservation.ReservationResponse;
import br.com.reservix.infrastructure.web.mapper.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final ReservationMapper reservationMapper;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final FindReservationByIdUseCase findReservationByIdUseCase;
    private final FindAllReservationsUseCase findAllReservationsUseCase;
    private final FindReservationsByUserUseCase findReservationsByUserUseCase;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation (@Valid @RequestBody CreateReservationRequest request, @AuthenticationPrincipal UserEntity user) {

        CreateReservationCommand command = reservationMapper.toCreateReservationCommand(request);
        ReservationDetailOutPut output = createReservationUseCase.execute(command, user.getId());
        ReservationResponse response = reservationMapper.toReservationResponse(output);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.id())
                .toUri();

        return ResponseEntity.created(location).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id) {

        ReservationDetailOutPut output = findReservationByIdUseCase.execute(id);

        ReservationResponse response = reservationMapper.toReservationResponse(output);


        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ReservationResponse>> findAllReservations(@RequestParam(required = false) LocalDateTime start,
                                                                         @RequestParam(required = false) LocalDateTime end,
                                                                         @RequestParam(required = false) Long roomId,
                                                                         @RequestParam(required = false) Long userId,
                                                                         @RequestParam(required = false) ReservationStatus status, Pageable pageable) {
        FindReservationsCommand command = new FindReservationsCommand(
                start,
                end,
                roomId,
                userId,
                status
        );

        Page<ReservationDetailOutPut> output =
                findAllReservationsUseCase.execute(command,pageable);


        Page<ReservationResponse> response =
                reservationMapper.toReservationResponse(output);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Page<ReservationResponse>> findMyReservations(
            Pageable pageable,
            @AuthenticationPrincipal UserEntity user) {

        Page<ReservationDetailOutPut> output =
                findReservationsByUserUseCase.execute(user.getId(), pageable);

        Page<ReservationResponse> response =
                reservationMapper.toReservationResponse(output);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable Long id,
            @AuthenticationPrincipal UserEntity user) {

        CancelReservationCommand command =
                new CancelReservationCommand(id, user.getId());

        cancelReservationUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }









}
