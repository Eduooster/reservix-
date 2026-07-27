package br.com.reservix.infrastructure.web.mapper;


import br.com.reservix.core.application.usecases.reservation.CreateReservationCommand;
import br.com.reservix.core.application.usecases.reservation.ReservationDetailOutPut;
import br.com.reservix.infrastructure.web.dtos.reservation.CreateReservationRequest;
import br.com.reservix.infrastructure.web.dtos.reservation.ReservationResponse;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    CreateReservationCommand toCreateReservationCommand(@Valid CreateReservationRequest request);

    ReservationResponse toReservationResponse(ReservationDetailOutPut outPut);



    default Page<ReservationResponse> toReservationResponse(Page<ReservationDetailOutPut> page) {
        return page.map(this::toReservationResponse);
    }

}
