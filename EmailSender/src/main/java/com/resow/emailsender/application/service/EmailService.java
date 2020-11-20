package com.resow.emailsender.application.service;

import com.resow.emailsender.application.dto.UserDTO;
import com.resow.emailsender.application.exception.EmailNotSendException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface EmailService {

    void send(UserDTO userDTO) throws EmailNotSendException;
}
