package br.com.reservix.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "reservation.exchange";
    public static final String QUEUE = "reservation.created.queue";
    public static final String ROUTING_KEY = "reservation.created";

    @Bean
    public TopicExchange reservationExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue reservationCreatedQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding reservationCreatedBinding(
            Queue reservationCreatedQueue,
            TopicExchange reservationExchange) {

        return BindingBuilder.bind(reservationCreatedQueue)
                .to(reservationExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}