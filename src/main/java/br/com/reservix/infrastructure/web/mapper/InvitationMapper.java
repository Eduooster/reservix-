package br.com.reservix.infrastructure.web.mapper;

import br.com.reservix.core.application.usecases.invitation.AcceptInvitationCommand;
import br.com.reservix.core.application.usecases.invitation.CreateInvitationCommand;
import br.com.reservix.core.application.usecases.invitation.InvitationDetailOutPut;
import br.com.reservix.infrastructure.web.dtos.invitation.AcceptInvitationRequest;
import br.com.reservix.infrastructure.web.dtos.invitation.CreateInvitationRequest;
import br.com.reservix.infrastructure.web.dtos.invitation.InvitationResponse;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface InvitationMapper {
    CreateInvitationCommand toCreateInvitationCommand(@Valid CreateInvitationRequest request);

    InvitationResponse toInvitationResponse(InvitationDetailOutPut output);


    AcceptInvitationCommand toAcceptInvitationCommand(UUID token, AcceptInvitationRequest request);


}
