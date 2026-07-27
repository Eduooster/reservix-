package br.com.reservix.core.application.usecases.invitation.usecases;

import br.com.reservix.core.application.ports.out.CompanyRepository;
import br.com.reservix.core.application.ports.out.DomainEventPublisher;
import br.com.reservix.core.application.ports.out.InvitationRepository;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.invitation.CreateInvitationCommand;
import br.com.reservix.core.application.usecases.invitation.InvitationAlreadyExistsException;
import br.com.reservix.core.application.usecases.invitation.InvitationDetailOutPut;
import br.com.reservix.core.domain.entities.company.Company;
import br.com.reservix.core.domain.entities.invitation.Invitation;
import br.com.reservix.core.domain.entities.invitation.InvitationStatus;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.application.usecases.company.exceptions.CompanyNotFoundException;
import br.com.reservix.core.application.usecases.user.UserAlreadyExistsException;
import br.com.reservix.core.application.usecases.user.UserNotFoundException;
import br.com.reservix.core.events.InvitationCreatedEvent;

public class CreateInvitationUseCase {

    private final InvitationRepository invitationRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;

    public CreateInvitationUseCase(InvitationRepository invitationRepository, CompanyRepository companyRepository, UserRepository userRepository, DomainEventPublisher domainEventPublisher) {
        this.invitationRepository = invitationRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public InvitationDetailOutPut execute(CreateInvitationCommand command, Long idInviter) {

        User inviter = userRepository.findById(idInviter).orElseThrow(() -> new UserNotFoundException("Inviter not found"));

        Company company = companyRepository.findById(inviter.getCompany().getId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));




        if (userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        if (invitationRepository.existsPendingInvitation(
                command.email(),
                company.getId(),
                InvitationStatus.PENDING
        )) {
            throw new InvitationAlreadyExistsException("Inviatioon already exists");
        }

        Invitation invitation = Invitation.create(
                company,
                command.email(),
                command.role(),
                inviter
        );

        Invitation savedInvitation = invitationRepository.save(invitation);

        domainEventPublisher.publish(
                new InvitationCreatedEvent(
                        savedInvitation.getId(),
                        savedInvitation.getToken(),
                        savedInvitation.getCompany().getName(),
                        inviter.getName(),
                        savedInvitation.getRole().toString(),
                        command.email()
                )
        );


        return new InvitationDetailOutPut(savedInvitation.getId());
    }
}
