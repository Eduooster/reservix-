package br.com.reservix.infrastructure.config;

import br.com.reservix.core.application.ports.out.*;
import br.com.reservix.core.application.usecases.company.usecases.*;
import br.com.reservix.core.application.usecases.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.reservix.core.application.usecases.auth.usecases.LoginUseCase;
import br.com.reservix.core.application.usecases.auth.usecases.RegisterUserUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.AcceptInvitationUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.CancelInvitationUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.CreateInvitationUseCase;
import br.com.reservix.core.application.usecases.invitation.usecases.FindInvitationByTokenUseCase;
import br.com.reservix.core.application.usecases.reservation.usecases.*;
import br.com.reservix.core.application.usecases.room.usecases.*;
import br.com.reservix.infrastructure.messaging.publisher.RabbitMqDomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public LoginUseCase loginUseCase(PassEncoderService passEncoderService, UserRepository useRepository, TokenServiceGateway tokenServiceGateway) {
        return new LoginUseCase(passEncoderService, useRepository, tokenServiceGateway);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder,TokenServiceGateway tokenServiceGateway) {
        return new RegisterUserUseCase(
                userRepository,passwordEncoder,tokenServiceGateway

        );
    }

    @Bean
    public GetAuthenticatedUserUseCase getAuthenticatedUserUseCase() {
        return new GetAuthenticatedUserUseCase();
    }

    @Bean
    public ListActiveRoomsUseCase listActiveRoomsUseCase() {
        return new ListActiveRoomsUseCase();
    }


    @Bean
    public CreateRoomUseCase createRoomUseCase(RoomRepository roomRepository,UserRepository userRepository) {
        return new CreateRoomUseCase(roomRepository,userRepository);
    }

    @Bean
    public UpdateRoomUseCase updateRoomUseCase(RoomRepository roomRepository) {
        return new UpdateRoomUseCase(roomRepository);
    }

    @Bean
    public DeleteRoomUseCase deleteRoomUseCase() {
        return new DeleteRoomUseCase();
    }

    @Bean
    public CheckRoomAvailabilityUseCase checkRoomAvailabilityUseCase() {
        return new CheckRoomAvailabilityUseCase();
    }

    @Bean
    public CreateReservationUseCase createReservationUseCase(ReservationRepository reservationRepository, RoomRepository roomRepository, UserRepository userRepository, RabbitMqDomainEventPublisher rabbitMqDomainEventPublisher) {
        return new CreateReservationUseCase(reservationRepository,roomRepository,userRepository,rabbitMqDomainEventPublisher);
    }

    @Bean
    public FindAllReservationsUseCase findAllReservationsUseCase(ReservationRepository reservationRepository) {
        return new FindAllReservationsUseCase(reservationRepository);
    }


    @Bean
    public FindReservationByIdUseCase findReservationByIdUseCase(ReservationRepository reservationRepository) {
        return new FindReservationByIdUseCase(reservationRepository);
    }

    @Bean
    public CancelReservationUseCase cancelReservationUseCase(ReservationRepository reservationRepository) {
        return new CancelReservationUseCase(reservationRepository);
    }

    @Bean
    public FindReservationsByUserUseCase findReservationsByUserUseCase(ReservationRepository reservationRepository) {
        return new FindReservationsByUserUseCase(reservationRepository);
    }

    @Bean
    public GetRoomDetailsUseCase getRoomDetailsUseCase(RoomRepository roomRepository) {
        return new GetRoomDetailsUseCase(roomRepository);
    }


    @Bean
    public CreateCompanyUseCase createCompanyUseCase(CompanyRepository companyRepository,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        return new CreateCompanyUseCase(companyRepository,userRepository,passwordEncoder);
    }

    @Bean
    public UpdateCompanyUseCase updateCompanyUseCase(CompanyRepository companyRepository) {
        return new UpdateCompanyUseCase(companyRepository);
    }

    @Bean
    public DeleteCompanyUseCase deleteCompanyUseCase(CompanyRepository companyRepository) {
        return new DeleteCompanyUseCase(companyRepository);
    }

    @Bean
    public FindCompanyByIdUseCase findCompanyByIdUseCase(CompanyRepository companyRepository) {
        return new FindCompanyByIdUseCase(companyRepository);
    }
    @Bean
    public FindAllCompaniesUseCase findAllCompaniesUseCase(CompanyRepository companyRepository) {
        return new FindAllCompaniesUseCase(companyRepository);
    }

    @Bean
    public CreateInvitationUseCase createInvitationUseCase(InvitationRepository invitationRepository, CompanyRepository companyRepository, UserRepository userRepository, RabbitMqDomainEventPublisher rabbitMqDomainEventPublisher) {
        return new CreateInvitationUseCase(invitationRepository,companyRepository,userRepository,rabbitMqDomainEventPublisher);
    }
    @Bean
    public CancelInvitationUseCase cancelInvitationUseCase() {
        return new CancelInvitationUseCase();
    }

    @Bean
    public AcceptInvitationUseCase acceptInvitationUseCase(InvitationRepository invitationRepository,UserRepository userRepository,PasswordEncoder passwordEncoder, RabbitMqDomainEventPublisher rabbitMqDomainEventPublisher   ) {
        return new AcceptInvitationUseCase(invitationRepository,userRepository,passwordEncoder,rabbitMqDomainEventPublisher);
    }

    @Bean
    public FindInvitationByTokenUseCase findInvitationByTokenUseCase() {
        return new FindInvitationByTokenUseCase();
    }

    @Bean
    public FindRoomAvaliabilityUseCase findRoomAvaliabilityUseCase(RoomRepository reservationRepository,ReservationRepository roomRepository) {
        return new FindRoomAvaliabilityUseCase(reservationRepository,roomRepository);
    }


}
