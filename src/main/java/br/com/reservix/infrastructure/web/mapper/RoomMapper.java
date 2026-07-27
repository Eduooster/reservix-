package br.com.reservix.infrastructure.web.mapper;

import br.com.reservix.core.application.usecases.reservation.usecases.RoomAvailabilityOutPut;
import br.com.reservix.core.application.usecases.room.AvailabilityOutput;
import br.com.reservix.core.application.usecases.room.CreateRoomCommand;
import br.com.reservix.core.application.usecases.room.DetailRoomOutput;
import br.com.reservix.core.application.usecases.room.UpdateRoomCommand;
import br.com.reservix.infrastructure.web.dtos.room.AvailabilityResponse;
import br.com.reservix.infrastructure.web.dtos.room.CreateRoomRequest;
import br.com.reservix.infrastructure.web.dtos.room.RoomResponse;
import br.com.reservix.infrastructure.web.dtos.room.UpdateRoomRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true),uses = {CompanyMapper.class})
public interface RoomMapper {

    CreateRoomCommand toRoomCreateCommand(CreateRoomRequest requestDTO);
    RoomResponse toRoomResponseDTO(DetailRoomOutput detailRoomOutPut);
    UpdateRoomCommand toUpdateRoomCommand(UpdateRoomRequest requestDTO);
    AvailabilityOutput toAvaliabilityOutput(AvailabilityResponse availabilityResponse);

    AvailabilityResponse toResponse(AvailabilityOutput availabilityOutput);

    default Page<AvailabilityResponse> toPageAvailabilityResponse(Page<AvailabilityOutput> page) {
        return page.map(this::toResponse);
    }

    default Page<RoomResponse> toPageRoomResponse(Page<DetailRoomOutput> page) {
        return page.map(this::toRoomResponseDTO);
    }


    AvailabilityResponse toAvailabilityResponse(RoomAvailabilityOutPut outPut);
}
