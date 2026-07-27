package br.com.reservix.infrastructure.messaging.consumer;

import br.com.reservix.core.events.ReservationCreatedEvent;


import br.com.reservix.infrastructure.messaging.handler.ReservationCreatedNotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationCreatedConsumer {

    private final ReservationCreatedNotificationHandler reservationCreatedNotificationHandler;


    @RabbitListener(queues = "reservation.created.queue")
    public void consume(ReservationCreatedEvent event) {
        reservationCreatedNotificationHandler.handle(event);

    }

}