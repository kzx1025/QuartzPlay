package recovery;

import exception.ExceptionJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by iceke on 17/6/5.
 */
public class TestScheduler {
    public static void main(String args[]){
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
            scheduler = schedulerFactory.getScheduler();
            JobDetail myJob = JobBuilder.newJob(RecoveryJob.class).withIdentity("job","jobGroup").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup1")
                    //每4s运行一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10))
                    .startNow()
                    .build();

            scheduler.scheduleJob(myJob,trigger);
            scheduler.start();

            Thread.sleep(10000);
            //它会等当前的job运行完 再暂停下一次调度的job
            scheduler.pauseJob(myJob.getKey());
            Thread.sleep(200000);
            scheduler.resumeJob(myJob.getKey());


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
