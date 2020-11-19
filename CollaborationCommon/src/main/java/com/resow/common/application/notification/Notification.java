package com.resow.common.application.notification;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public interface Notification<T> {

    public void notify(T event);

}
