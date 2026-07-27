package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.domain.entities.invitation.Invitation;
import br.com.reservix.core.domain.entities.invitation.InvitationStatus;

import java.util.Optional;
import java.util.UUID;

public interface InvitationRepository {
    boolean existsPendingInvitation(String email, Long companyId, InvitationStatus status);

    Invitation save(Invitation invitation);

    Optional<Invitation> findByToken(UUID token);
}
