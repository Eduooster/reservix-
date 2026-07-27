package br.com.reservix.infrastructure.persistence.mapper;


import br.com.reservix.core.domain.entities.invitation.Invitation;
import br.com.reservix.infrastructure.persistence.entities.InvitationEntitiy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvitationEntityMapper {
    Invitation toDomain(InvitationEntitiy invitationEntitiy);
    InvitationEntitiy toEntity( Invitation invitation);
}
