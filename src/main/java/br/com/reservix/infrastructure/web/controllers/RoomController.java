package br.com.reservix.infrastructure.web.controllers;

import br.com.reservix.core.application.usecases.reservation.usecases.RoomAvailabilityOutPut;
import br.com.reservix.core.application.usecases.room.*;
import br.com.reservix.core.application.usecases.room.usecases.*;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import br.com.reservix.infrastructure.web.dtos.room.AvailabilityResponse;
import br.com.reservix.infrastructure.web.dtos.room.CreateRoomRequest;
import br.com.reservix.infrastructure.web.dtos.room.RoomResponse;
import br.com.reservix.infrastructure.web.dtos.room.UpdateRoomRequest;
import br.com.reservix.infrastructure.web.mapper.RoomMapper;
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
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final ListActiveRoomsUseCase listActiveRoomsUseCase;
    private final GetRoomDetailsUseCase getRoomDetailsUseCase;
    private final CreateRoomUseCase createRoomUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;
    private final CheckRoomAvailabilityUseCase checkRoomAvailabilityUseCase;
    private final FindRoomAvaliabilityUseCase findRoomAvaliabilityUseCase;
    private final RoomMapper roomMapper;



    @GetMapping
    public ResponseEntity<Page<RoomResponse>> listActiveRooms(Pageable pageable) {
        Page<DetailRoomOutput> output = listActiveRoomsUseCase.execute(pageable);
        Page<RoomResponse> response = roomMapper.toPageRoomResponse(output);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomDetails(@PathVariable Long id) {
        DetailRoomOutput output = getRoomDetailsUseCase.execute(id);
        RoomResponse response = roomMapper.toRoomResponseDTO(output);

        return ResponseEntity.ok(response);
    }

    @PostMapping

    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody CreateRoomRequest request,@AuthenticationPrincipal UserEntity user) {
        log.info("Create Room" + request.toString());

        CreateRoomCommand command = roomMapper.toRoomCreateCommand(request);
        command.setUserId(user.getId());


        DetailRoomOutput output = createRoomUseCase.execute(command);

        RoomResponse response = roomMapper.toRoomResponseDTO(output);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @RequestBody UpdateRoomRequest requestDTO) {


        UpdateRoomCommand command = roomMapper.toUpdateRoomCommand(requestDTO);
        DetailRoomOutput output = updateRoomUseCase.execute(id,command);
        RoomResponse response = roomMapper.toRoomResponseDTO(output);


        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        deleteRoomUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roomId}/availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @PathVariable Long roomId,
            @RequestParam LocalDate date) {

        FindRoomAvailabilityCommand command = new FindRoomAvailabilityCommand(roomId, date);
        RoomAvailabilityOutPut outPut = findRoomAvaliabilityUseCase.execute(command);
        AvailabilityResponse response = roomMapper.toAvailabilityResponse(outPut);





        return ResponseEntity.ok(response);
    }
}
