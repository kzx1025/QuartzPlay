package cluster;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by iceke on 17/6/5.
 */
public class TestScheduler {
    public static void main(String args[]) throws Exception{

            boolean clearJobs = false;
            boolean scheduleJobs = true;

            for (String arg : args) {
                if (arg.equalsIgnoreCase("clearJobs")) {
                    clearJobs = true;
                } else if (arg.equalsIgnoreCase("dontScheduleJobs")) {
                    scheduleJobs = false;
                }
            }

            TestScheduler example = new TestScheduler();
            example.run(clearJobs, scheduleJobs);

    }

    private void run(boolean inClearJobs, boolean inScheduleJobs) throws Exception{
        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        if (inClearJobs) {
            System.out.println("***** Deleting existing jobs/triggers *****");
            sched.clear();
        }

        System.out.println("------- Initialization Complete -----------");

        if (inScheduleJobs) {

            System.out.println("------- Scheduling Jobs ------------------");

            String schedId = sched.getSchedulerInstanceId();

            int count = 1;

            JobDetail job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group
                    // named after the cluster
                    // node instance just to
                    // distinguish (in logging)
                    // what was scheduled from
                    // where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId)
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();

           System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            count++;

            job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group named after
                    // the cluster node instance just to
                    // distinguish (in logging) what was
                    // scheduled from where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();

            System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            count++;

            job = JobBuilder.newJob(SimpleRecoveryStatefulJob.class).withIdentity("job_" + count, schedId) // put triggers in group named
                    // after the cluster node
                    // instance just to
                    // distinguish (in logging)
                    // what was scheduled from
                    // where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(3)).build();

            System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            count++;

            job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group named after
                    // the cluster node instance just to
                    // distinguish (in logging) what was
                    // scheduled from where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(4)).build();

            System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount()
                    + "/" + trigger.getRepeatInterval());
            sched.scheduleJob(job, trigger);

            count++;

            job = JobBuilder.newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group named after
                    // the cluster node instance just to
                    // distinguish (in logging) what was
                    // scheduled from where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = TriggerBuilder.newTrigger().withIdentity("triger_" + count, schedId).startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInMilliseconds(4500L)).build();

            System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount()
                    + "/" + trigger.getRepeatInterval());
            sched.scheduleJob(job, trigger);
        }

        // jobs don't start firing until start() has been called...
        System.out.println("------- Starting Scheduler ---------------");
        sched.start();
        System.out.println("------- Started Scheduler ----------------");

        System.out.println("------- Waiting for one hour... ----------");
        try {
            Thread.sleep(3600L * 1000L);
        } catch (Exception e) {
            //
        }

        System.out.println("------- Shutting Down --------------------");
        sched.shutdown();
        System.out.println("------- Shutdown Complete ----------------");

    }
}
