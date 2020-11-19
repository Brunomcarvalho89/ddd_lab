package com.resow.authenticationidentity.application;

import com.resow.authenticationidentity.domain.BeanFacades;
import com.resow.common.event.EventPublisher;
import com.resow.common.event.EventSubscriber;
import com.resow.common.utils.hash.IHashFunction;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ApplicationStart {

    private static ApplicationStart applicationStart;

    private ApplicationStart() {
    }

    public static ApplicationStart instance() {
        if (ApplicationStart.applicationStart == null) {
            ApplicationStart.applicationStart = new ApplicationStart();
        }
        return ApplicationStart.applicationStart;
    }

    public ApplicationStart clear() {

        if (ApplicationStart.applicationStart != null) {
            EventPublisher.instance().clearSubscribers();
        }

        ApplicationStart.applicationStart = new ApplicationStart();
        return ApplicationStart.applicationStart;
    }

    public ApplicationStart subscribeEvent(EventSubscriber eventSubscriber) {

        EventPublisher
                .instance()
                .subscribe(eventSubscriber);

        return this;
    }

    public ApplicationStart initializeBeansFacade(IHashFunction iHashFunction) {
        BeanFacades.initialize(iHashFunction);
        return this;
    }

    public void start() {
    }
}
