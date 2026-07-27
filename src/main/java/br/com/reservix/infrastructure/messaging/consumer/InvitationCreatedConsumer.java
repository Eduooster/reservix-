package br.com.reservix.infrastructure.messaging.consumer;


import br.com.reservix.core.events.InvitationCreatedEvent;
import br.com.reservix.infrastructure.messaging.handler.InvitationCreatedNotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvitationCreatedConsumer {

    private final InvitationCreatedNotificationHandler invitationCreatedNotificationHandler;


    @RabbitListener(queues = "reservation.created.queue")
    public void consume(InvitationCreatedEvent event) {
        invitationCreatedNotificationHandler.handle(event);

    }

}
