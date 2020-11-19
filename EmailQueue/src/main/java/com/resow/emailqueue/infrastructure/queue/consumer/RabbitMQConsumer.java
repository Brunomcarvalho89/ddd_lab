package com.resow.emailqueue.infrastructure.queue.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.resow.emailqueue.application.dto.UserDTO;
import com.resow.emailqueue.application.exception.EmailNotSendException;
import com.resow.emailqueue.application.service.impl.EmailServiceUserCreated;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.resow.emailqueue.application.service.IEmailSender;
import com.resow.emailqueue.domain.service.IEmailFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Component
public class RabbitMQConsumer {

    private static final Logger LOG = Logger.getLogger(RabbitMQConsumer.class.getName());

    @Autowired
    private IEmailSender emailSender;

    @Autowired
    private IEmailFactory emailFactory;

    @Value("${email.from}")
    private String from;

    @RabbitListener(queues = "${queue.email.user.created}")
    public void recievedMessage(String sUserCreated, Message message, Channel channel) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            UserDTO userCreated = objectMapper.readValue(sUserCreated, UserDTO.class);

            new EmailServiceUserCreated(this.emailSender, this.emailFactory, this.from)
                    .send(userCreated);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (JsonProcessingException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (IOException | EmailNotSendException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }
}
