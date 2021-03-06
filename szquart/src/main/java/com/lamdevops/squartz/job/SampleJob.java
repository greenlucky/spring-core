package com.lamdevops.squartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleJob  implements Job {

    private final Logger logger = LoggerFactory.getLogger(SampleJob.class);
    
    @Override
    public void execute(JobExecutionContext context) {
        logger.info("This is SampleJob!!!");
    }
}
