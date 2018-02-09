package com.lamdevops.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventCreator {

    private static final Logger logger = LoggerFactory.getLogger(EventCreator.class);

    @Scheduled(cron = "0/20 * * * * ?")
    public void create() {
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Event created at [{}]", start);
    }

    @Scheduled(cron = "00 53 16 9 02 ?")
    public void pushEvent() {
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Event pushed at [{}]", start);
    }
}
