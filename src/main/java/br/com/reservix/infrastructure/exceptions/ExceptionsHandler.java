package br.com.reservix.infrastructure.exceptions;

import br.com.reservix.core.application.usecases.company.exceptions.CompanyAlreadyExistsException;
import br.com.reservix.core.application.usecases.company.exceptions.CompanyNotFoundException;
import br.com.reservix.core.application.usecases.invitation.InvitationAlreadyExistsException;
import br.com.reservix.core.application.usecases.invitation.InvitationNotFoundException;
import br.com.reservix.core.application.usecases.reservation.ReservationNotFoundException;
import br.com.reservix.core.application.usecases.room.RoomNotFound;
import br.com.reservix.core.application.usecases.room.RoomNotFoundException;
import br.com.reservix.core.application.usecases.user.EmailAlreadyInUseException;
import br.com.reservix.core.application.usecases.user.UserAlreadyExistsException;
import br.com.reservix.core.application.usecases.user.UserNotFoundException;
import br.com.reservix.core.domain.entities.company.CompanyAlreadyInactiveException;
import br.com.reservix.core.domain.entities.invitation.InvalidReservationPeriodException;
import br.com.reservix.core.domain.entities.reservation.*;
import br.com.reservix.core.domain.entities.room.RoomAlreadyReservedException;
import br.com.reservix.core.domain.entities.room.RoomInactiveException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler({
            CompanyNotFoundException.class,
            InvitationNotFoundException.class,
            ReservationNotFoundException.class,
            RoomNotFoundException.class,
            RoomNotFound.class,
            UserNotFoundException.class
    })
    public ProblemDetail handleNotFoundExceptions(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler({
            CompanyAlreadyExistsException.class,
            EmailAlreadyInUseException.class,
            InvitationAlreadyExistsException.class,
            ReservationAlreadyCancelledException.class,
            RoomAlreadyReservedException.class,
            UserAlreadyExistsException.class
    })
    public ProblemDetail handleConflictExceptions(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }


    @ExceptionHandler({
            CompanyAlreadyInactiveException.class,
            InvalidReservationPeriodException.class,
            InvitationExpiredException.class,
            ReservationInPastException.class,
            ReservationMaximumAdvanceExceededException.class,
            ReservationMaximumDurationExceededException.class,
            RoomInactiveException.class
    })
    public ProblemDetail handleBadRequestExceptions(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 403 - FORBIDDEN (Acesso negado)
    @ExceptionHandler(ReservationAccessDeniedException.class)
    public ProblemDetail handleForbiddenExceptions(ReservationAccessDeniedException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
    }


    @ExceptionHandler(NotificationDeliveryException.class)
    public ProblemDetail handleInternalServerErrorExceptions(NotificationDeliveryException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno de infraestrutura: " + ex.getMessage());
    }
}
