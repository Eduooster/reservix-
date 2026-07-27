package br.com.reservix.infrastructure.persistence.repositories;

import br.com.reservix.core.domain.entities.invitation.InvitationStatus;
import br.com.reservix.infrastructure.persistence.entities.InvitationEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaInvitationRepository extends JpaRepository<InvitationEntitiy,Long> {
    boolean existsByEmailAndCompanyIdAndStatus(
            String email,
            Long companyId,
            InvitationStatus status
    );

    Optional<InvitationEntitiy> findByToken(UUID token);
}
