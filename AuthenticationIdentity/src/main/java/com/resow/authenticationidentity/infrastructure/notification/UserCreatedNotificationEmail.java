package com.resow.authenticationidentity.infrastructure.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resow.authenticationidentity.application.dto.UserDTO;
import com.resow.authenticationidentity.application.event.UserCreated;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.resow.authenticationidentity.application.notification.UserCreatedNotification;
import com.resow.authenticationidentity.application.service.IUserDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
@Component
public class UserCreatedNotificationEmail implements UserCreatedNotification {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.email}")
    public String exchangeNameEmail;

    @Value("${queue.email.user.created}")
    public String queueEmailUserCreated;

    @Value("${queue.routing.key.email.user.created}")
    public String queueBindingEmailUserCreated;

    private IUserDataService userDataService;

    @Autowired
    public UserCreatedNotificationEmail(IUserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public void notify(UserCreated userCreated) {

        UserDTO userDto = this.userDataService.allUserDataByUUID(userCreated.userUUID().toString());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userDto);
            rabbitTemplate.convertAndSend(exchangeNameEmail, queueBindingEmailUserCreated, json);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
