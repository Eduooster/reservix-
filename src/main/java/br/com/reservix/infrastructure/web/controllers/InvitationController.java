package br.com.reservix.infrastructure.web.controllers;

import br.com.reservix.core.application.usecases.invitation.*;
import br.com.reservix.core.application.usecases.invitation.usecases.AcceptInvitationUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.CancelInvitationUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.CreateInvitationUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.FindInvitationByTokenUseCase;
import br.com.reservix.infrastructure.persistence.entities.UserEntity;
import br.com.reservix.infrastructure.web.mapper.InvitationMapper;
import br.com.reservix.infrastructure.web.dtos.invitation.AcceptInvitationRequest;
import br.com.reservix.infrastructure.web.dtos.invitation.CreateInvitationRequest;
import br.com.reservix.infrastructure.web.dtos.invitation.InvitationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/invitations")
@RequiredArgsConstructor
public class InvitationController {

    private final CreateInvitationUseCase createInvitationUseCase;
    private final FindInvitationByTokenUseCase findInvitationByTokenUseCase;
    private final AcceptInvitationUseCase acceptInvitationUseCase;
    private final CancelInvitationUseCase cancelInvitationUseCase;

    private final InvitationMapper invitationMapper;

    @PostMapping
    public ResponseEntity<InvitationResponse> createInvitation(
            @Valid @RequestBody CreateInvitationRequest request,
            @AuthenticationPrincipal UserEntity user) {

        CreateInvitationCommand command =
                invitationMapper.toCreateInvitationCommand(request);

        InvitationDetailOutPut output =
                createInvitationUseCase.execute(command,user.getId());

        InvitationResponse response =
                invitationMapper.toInvitationResponse(output);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{token}")
    public ResponseEntity<InvitationResponse> findByToken(
            @PathVariable String token) {

        InvitationDetailOutPut output =
                findInvitationByTokenUseCase.execute(token);

        InvitationResponse response =
                invitationMapper.toInvitationResponse(output);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{token}/accept")
    public ResponseEntity<Void> acceptInvitation(
            @PathVariable UUID token,
            @Valid @RequestBody AcceptInvitationRequest request) {



        AcceptInvitationCommand command =
                invitationMapper.toAcceptInvitationCommand(token, request);

        acceptInvitationUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelInvitation(
            @PathVariable Long id) {

        cancelInvitationUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }

}