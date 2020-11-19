package com.resow.common.event;

import java.lang.reflect.ParameterizedType;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public abstract class AbstractEventSubscriber<T> implements EventSubscriber<T> {

    public AbstractEventSubscriber() {
    }

    @Override
    public Class<T> subscribedToEventType() {
        Class<T> eventType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return eventType;
    }

}
