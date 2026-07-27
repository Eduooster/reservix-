package br.com.reservix.infrastructure.messaging.publisher;

import br.com.reservix.core.application.ports.out.DomainEventPublisher;
import br.com.reservix.core.events.DomainEvent;
import br.com.reservix.infrastructure.config.RabbitMQConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqDomainEventPublisher implements DomainEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqDomainEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );

    }
}
