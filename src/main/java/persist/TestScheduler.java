package persist;

import exception.ExceptionJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;

/**
 * Created by iceke on 17/6/4.
 */
public class TestScheduler {
    public static void main(String args[]){
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
            scheduler = schedulerFactory.getScheduler();
            JobDetail myJob1 = JobBuilder.newJob(DataPersistJob.class).withIdentity("job1","jobGroup").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup")
                    //每4s运行一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4).withRepeatCount(10))
                    .startNow()
                    .build();

            myJob1.getJobDataMap().put(DataPersistJob.COUNT,0);
            myJob1.getJobDataMap().put(DataPersistJob.STATES,new ArrayList<String>());
            scheduler.scheduleJob(myJob1,trigger);



            JobDetail myJob2 = JobBuilder.newJob(DataPersistJob.class).withIdentity("job2","jobGroup").build();
            Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2","triggerGroup")
                    //每4s运行一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4).withRepeatCount(10))
                    .startNow()
                    .build();

            myJob2.getJobDataMap().put(DataPersistJob.COUNT,1024);
            myJob2.getJobDataMap().put(DataPersistJob.STATES,new ArrayList<String>());
            scheduler.scheduleJob(myJob2,trigger2);


            scheduler.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
