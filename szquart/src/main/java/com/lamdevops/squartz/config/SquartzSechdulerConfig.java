package com.lamdevops.squartz.config;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.io.IOException;

@Configuration
public class SquartzSechdulerConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job) throws IOException, SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        factory.initialize(new ClassPathResource("quartz.properties").getInputStream());

        Scheduler scheduler = factory.getScheduler();
        scheduler.deleteJob(job.getKey());
        scheduler.setJobFactory(springBeanJobFactory());
        /*if (scheduler.checkExists(job.getKey()) && scheduler.checkExists(trigger.getJobKey()))
            scheduler.rescheduleJob(trigger.getKey(), trigger);
        else*/
        scheduler.scheduleJob(job, trigger);

        scheduler.start();
        return scheduler;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
}
