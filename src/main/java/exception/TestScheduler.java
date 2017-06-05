package exception;

import cron.CronJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by iceke on 17/6/3.
 */
public class TestScheduler {
    public static void main(String args[]){
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
            scheduler = schedulerFactory.getScheduler();
            JobDetail myJob = JobBuilder.newJob(ExceptionJob.class).withIdentity("job","jobGroup").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup1")
                    //每4s运行一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4).withRepeatCount(10))
                    .startNow()
                    .build();

            scheduler.scheduleJob(myJob,trigger);
            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
