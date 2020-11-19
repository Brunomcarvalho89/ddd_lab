package com.resow.authenticationidentity.application.event.subscribers;

import com.resow.authenticationidentity.application.event.UserCreated;
import com.resow.authenticationidentity.application.notification.UserCreatedNotification;
import com.resow.common.event.AbstractEventSubscriber;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class CreatedUserSubscriber extends AbstractEventSubscriber<UserCreated> {

    private final UserCreatedNotification notification;

    public CreatedUserSubscriber(UserCreatedNotification notification) {
        this.notification = notification;
    }

    @Override
    public void handleEvent(UserCreated event) {
        this.notification.notify(event);
    }

}
