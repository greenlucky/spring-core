package com.lamdevops.squartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.annotation.PostConstruct;

public class AutoWiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(AutoWiringSpringBeanJobFactory.class);

    private transient AutowireCapableBeanFactory beanJobFactory;

    @PostConstruct
    public void init() {
        logger.info("Hello initialize Squartz...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beanJobFactory = applicationContext.getAutowireCapableBeanFactory();
    }
    @Override
    protected  Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanJobFactory.autowireBean(job);
        return job;
    }
}
