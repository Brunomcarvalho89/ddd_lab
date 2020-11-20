package com.resow.emailqueue.infrastructure;

import com.resow.emailqueue.infrastructure.queue.consumer.RabbitMQConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Configuration
public class RabbitMQConfig {

    @Value("${exchange.email}")
    public String EXCHANGE_NAME_EMAIL;

    @Value("${queue.email.user.created}")
    public String queueEmailUserCreated;

    @Value("${queue.routing.key.email.user.created}")
    public String queueBindingEmailUserCreated;

    @Bean
    public Queue queue() {
        return new Queue(queueEmailUserCreated, Boolean.TRUE);
    }

    @Bean(name = "exchange-email")
    public DirectExchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME_EMAIL)
                .durable(Boolean.TRUE)
                .build();
    }

    @Bean
    public Binding binding(Queue queue, @Qualifier("exchange-email") DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queueBindingEmailUserCreated);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueEmailUserCreated);
        container.setMessageListener(listenerAdapter);
        container.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable thrwbl) {
                System.out.println(thrwbl.getMessage());
            }
        });
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMQConsumer receiver) {
        return new MessageListenerAdapter(receiver, "recievedMessage");
    }

}
