package com.resow.emailqueue.application.service;

import com.resow.emailqueue.application.dto.UserDTO;
import com.resow.emailqueue.application.exception.EmailNotSendException;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface EmailService {

    void send(UserDTO userDTO) throws EmailNotSendException;
}
