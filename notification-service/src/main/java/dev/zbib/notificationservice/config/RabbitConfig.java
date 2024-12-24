package dev.zbib.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange("notification.exchange");
    }


    @Bean
    public Queue bookingNotificationQueue() {
        return new Queue("booking.queue");
    }

    @Bean
    public Queue userNotificationQueue() {
        return new Queue("user.queue");
    }

    @Bean
    public Queue providerNotificationQueue() {
        return new Queue("provider.queue");
    }

    @Bean
    public Binding bookingBinding(
            Queue bookingNotificationQueue,
            TopicExchange notificationExchange) {
        return BindingBuilder.bind(bookingNotificationQueue)
                .to(notificationExchange)
                .with("booking.notification.#");
    }

    @Bean
    public Binding userBinding(
            Queue userNotificationQueue,
            TopicExchange notificationExchange) {
        return BindingBuilder.bind(userNotificationQueue)
                .to(notificationExchange)
                .with("user.notification.#");
    }

    @Bean
    public Binding providerBinding(
            Queue providerNotificationQueue,
            TopicExchange notificationExchange) {
        return BindingBuilder.bind(providerNotificationQueue)
                .to(notificationExchange)
                .with("provider.notification.#");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
