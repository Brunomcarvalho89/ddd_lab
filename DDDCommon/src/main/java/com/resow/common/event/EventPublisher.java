package com.resow.common.event;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class EventPublisher {

    private static final EventPublisher instance = new EventPublisher();

    private List<EventSubscriber<? extends BaseEvent>> subscribers;

    private EventPublisher() {
        this.subscribers = new ArrayList<>();
    }

    public static EventPublisher instance() {
        return instance;
    }

    public <T extends BaseEvent> void publish(T domainEvent) {

        Class<?> eventType = domainEvent.getClass();

        this.subscribers()
                .stream()
                .filter((subscriber) -> (eventType == subscriber.subscribedToEventType() || subscriber.subscribedToEventType() == BaseEvent.class))
                .forEach((EventSubscriber subscriber) -> {
                    subscriber.handleEvent(domainEvent);
                });
    }

    public <T extends BaseEvent> void subscribe(EventSubscriber<T> subscriber) {
        this.subscribers().add(subscriber);
    }

    public List<EventSubscriber<? extends BaseEvent>> subscribers() {
        return subscribers;
    }

    public void clearSubscribers() {
        this.subscribers.clear();
    }
}
