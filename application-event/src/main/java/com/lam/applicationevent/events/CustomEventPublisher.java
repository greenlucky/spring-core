package com.lam.applicationevent.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublisherAnEvent(final  String message) {
        System.out.println("Publishing custom event.");
        CustomEvent customEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customEvent);
    }
}
