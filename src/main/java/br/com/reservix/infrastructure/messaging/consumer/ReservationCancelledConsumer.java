package br.com.reservix.infrastructure.messaging.consumer;

import br.com.reservix.core.events.ReservationCancelledEvent;
import br.com.reservix.infrastructure.messaging.handler.ReservationCancelledNotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RequiredArgsConstructor

public class ReservationCancelledConsumer {

    private final ReservationCancelledNotificationHandler reservationCancelledNotificationHandler;


    @RabbitListener(queues = "reservation.cancelled.queue")
    public void consume(ReservationCancelledEvent event) {
        reservationCancelledNotificationHandler.handle(event);

    }
}
