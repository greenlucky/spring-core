package com.lam.applicationevent.frameworkEvents;

import com.lam.applicationevent.events.CustomEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextReshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cse) {
        System.out.println("Handling context re-freshed event.");
        if(cse.getSource() instanceof CustomEvent) {
            System.out.println("CSE received custom event - " + ((CustomEvent)cse.getSource()).getMessage());
        }
    }
}
