package com.lam.applicationevent.frameworkEvents;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextEventListener {

    @EventListener
    public void handleContextRefreshEvent(ContextStartedEvent ctxStartEvt) {
        System.out.println("Context Start Event received.");
    }

    @EventListener(classes = {ContextStartedEvent.class, ContextStartedEvent.class})
    public void handleMultipleEvents() {
        System.out.println("Multi-event listener invoked.");
    }
}
