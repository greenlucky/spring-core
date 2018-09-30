package com.lam.applicationevent.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomEventPublisherTest {

    @Autowired
    private CustomEventPublisher customEventPublisher;

    @Test
    @EventListener(classes = {ContextStartedEvent.class, ContextStartedEvent.class})
    public void doStuffAndPublisherAnEvent() {
        String message = "Hi, this is a custom event publisher!";
        customEventPublisher.doStuffAndPublisherAnEvent(message);
    }
}