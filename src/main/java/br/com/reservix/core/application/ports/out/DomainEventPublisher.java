package br.com.reservix.core.application.ports.out;

import br.com.reservix.core.events.DomainEvent;


public interface DomainEventPublisher {

    void publish(DomainEvent event);

}