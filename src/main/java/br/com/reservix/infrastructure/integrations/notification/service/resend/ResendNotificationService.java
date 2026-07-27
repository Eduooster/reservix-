package br.com.reservix.infrastructure.integrations.notification.service.resend;


import br.com.reservix.core.application.ports.out.NotificationService;


import br.com.reservix.core.application.usecases.invitation.InvitationCreatedNotification;
import br.com.reservix.core.application.usecases.reservation.ReservationCancelledNotification;
import br.com.reservix.core.application.usecases.reservation.ReservationCreatedNotification;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import com.resend.services.emails.model.Template;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResendNotificationService implements NotificationService {


    private final Resend resend = new Resend(System.getenv("RESEND_TOKEN"));


    @Override
    public String sendReservationCreated(ReservationCreatedNotification notification) {


        Map<String, Object> variables = new HashMap<>();

        variables.put("user_name", notification.userName());
        variables.put("room_name", notification.roomName());
        variables.put("company_name", notification.companyName());
        variables.put("reservation_date", notification.reservationDate().toString());
        variables.put("start_time", notification.startTime().toString());
        variables.put("end_time", notification.endTime().toString());
        variables.put("reservation_url", notification.reservationUrl());


        return sendTemplateEmail(
                notification.userEmail(),
                "Invitation from " + notification.companyName(),
                "reserva-confirmada",
                variables
        );



    }


    @Override
    public String sendInvitationCreated(InvitationCreatedNotification notification) {



        Map<String, Object> variables = new HashMap<>();


        variables.put("company_name", notification.comapanyName());
        variables.put("inviter_name", notification.inviterName());
        variables.put("role_name", notification.role());
        variables.put("invite_url", "reservix.com");
        variables.put("year", 2026);


        return sendTemplateEmail(
                notification.inviterName(),
                "Invitation from " + notification.comapanyName(),
                "invitation-accepted",
                variables
        );
    }

    @Override
    public String sendReservationCancelled(ReservationCancelledNotification notification) {



        Map<String, Object> variables = new HashMap<>();

        variables.put("user_name", notification.userName());
        variables.put("room_name", notification.roomName());
        variables.put("company_name", notification.companyName());

        variables.put("company_name", notification.companyName());
        variables.put("start_time", notification.startTime().toString());
        variables.put("end_time", notification.endTime().toString());

        variables.put("reservation_date", "reservix.com");

        variables.put("year", 2026);


        return sendTemplateEmail(
                notification.userEmail(),
                "Reserva cancelada",
                "reservation-cancelled",
                variables
        );
    }


    private String sendTemplateEmail(
            String recipient,
            String subject,
            String templateId,
            Map<String, Object> variables
    ) {


        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to(recipient)
                .subject(subject)
                .template(Template.builder()
                        .id(templateId)
                        .variables(variables)
                        .build())
                .build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            return response.getId();
        } catch (ResendException e) {
            throw new RuntimeException("Failed to send email.", e);
        }
    }
}
