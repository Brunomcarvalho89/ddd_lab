package com.resow.common.event;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 * @param <T>
 */
public interface EventSubscriber<T> {

    public Class<T> subscribedToEventType();

    public void handleEvent(T event);

}
