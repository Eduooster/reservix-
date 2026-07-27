package br.com.reservix.core.application.usecases.invitation.usecases;

import br.com.reservix.core.application.ports.out.DomainEventPublisher;
import br.com.reservix.core.application.ports.out.InvitationRepository;
import br.com.reservix.core.application.ports.out.UserRepository;
import br.com.reservix.core.application.usecases.invitation.AcceptInvitationCommand;
import br.com.reservix.core.application.usecases.invitation.InvitationNotFoundException;
import br.com.reservix.core.domain.entities.invitation.Invitation;
import br.com.reservix.core.domain.entities.invitation.InvitationStatus;
import br.com.reservix.core.domain.entities.User;
import br.com.reservix.core.domain.entities.reservation.InvitationExpiredException;
import br.com.reservix.core.application.usecases.user.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Slf4j
public class AcceptInvitationUseCase {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DomainEventPublisher domainEventPublisher;

    public AcceptInvitationUseCase(InvitationRepository invitationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DomainEventPublisher domainEventPublisher) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void execute(AcceptInvitationCommand command) {

        log.info("Executing command {}", command);

        Invitation invitation = invitationRepository
                .findByToken(command.token())
                .orElseThrow(() ->
                        new InvitationNotFoundException("Invitation not found"));

        if (invitation.getStatus() != InvitationStatus.PENDING) {
            throw new InvitationNotFoundException("Invitation is no longer valid.");
        }

        if (invitation.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvitationExpiredException("Invitation has expired.");
        }

        if (userRepository.existsByEmail(invitation.getEmail())) {
            throw new UserAlreadyExistsException("User already exists.");
        }

        User user = User.create(
                command.name(),
                invitation.getEmail(),
                passwordEncoder.encode(command.password()),
                invitation.getRole(),
                invitation.getCompany()

        );

        User savedUser = userRepository.save(user);

        invitation.accept();

        invitationRepository.save(invitation);


    }
}
