package cron;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by iceke on 17/5/31.
 */
public class TestScheduler {
    public static void main(String args[]){
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
            scheduler = schedulerFactory.getScheduler();
            JobDetail myJob = JobBuilder.newJob(CronJob.class).withIdentity("job1","jobGroup1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 30 11 * * ? *"))
                    .startNow()
                    .build();

            scheduler.scheduleJob(myJob,trigger);
            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
