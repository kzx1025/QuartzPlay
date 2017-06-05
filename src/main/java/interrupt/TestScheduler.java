package interrupt;

import exception.ExceptionJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by iceke on 17/6/4.
 */
public class TestScheduler {
    public static  void main(String args[]){
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
            scheduler = schedulerFactory.getScheduler();
            JobDetail myJob = JobBuilder.newJob(InterruptJob.class).withIdentity("job","jobGroup").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup1")
                    //每4s运行一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10))
                    .startNow()
                    .build();

            scheduler.scheduleJob(myJob,trigger);
            scheduler.start();

            for(int i =0;i<50;i++){
                try{
                    Thread.sleep(7000);
                    scheduler.interrupt(myJob.getKey());
                }catch (Exception e){

                }
            }
            scheduler.shutdown(true);

            SchedulerMetaData metaData = scheduler.getMetaData();
            System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
