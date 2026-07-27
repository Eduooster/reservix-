package br.com.reservix.infrastructure.persistence.adapters;

import br.com.reservix.core.application.ports.out.InvitationRepository;
import br.com.reservix.core.domain.entities.invitation.Invitation;
import br.com.reservix.core.domain.entities.invitation.InvitationStatus;
import br.com.reservix.infrastructure.persistence.entities.InvitationEntitiy;
import br.com.reservix.infrastructure.persistence.mapper.InvitationEntityMapper;
import br.com.reservix.infrastructure.persistence.repositories.JpaInvitationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvitationRepositoryImpl implements InvitationRepository {

    private final JpaInvitationRepository jpaInvitationRepository;
    private final InvitationEntityMapper invitationEntityMapper;



    @Override
    public boolean existsPendingInvitation(String email, Long id, InvitationStatus status) {
        return jpaInvitationRepository
                .existsByEmailAndCompanyIdAndStatus(email, id,status);

    }

    @Override
    public Invitation save(Invitation invitation) {

       InvitationEntitiy entity = invitationEntityMapper.toEntity(invitation);
       jpaInvitationRepository.save(entity);

       Invitation domain = invitationEntityMapper.toDomain(entity);
       return domain;

    }

    @Override
    public Optional<Invitation> findByToken(UUID token) {
        return jpaInvitationRepository.findByToken(token).map(invitationEntityMapper::toDomain);
    }
}
