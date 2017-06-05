package cluster;

import org.quartz.*;

import java.util.Date;

/**
 * Created by iceke on 17/6/5.
 */
public class SimpleRecoveryJob implements Job{
    public static final String COUNT = "count";
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();

        // if the job is recovering print a message
        if (context.isRecovering()) {
            System.out.println("SimpleRecoveryJob: " + jobKey + " RECOVERING at " + new Date());
        } else {
            System.out.println("SimpleRecoveryJob: " + jobKey + " starting at " + new Date());
        }

        // delay for ten seconds
        long delay = 10L * 1000L;
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            //
        }

        JobDataMap data = context.getJobDetail().getJobDataMap();
        int count;
        if (data.containsKey(COUNT)) {
            count = data.getInt(COUNT);
        } else {
            count = 0;
        }
        count++;
        data.put(COUNT, count);

        System.out.println("SimpleRecoveryJob: " + jobKey + " done at " + new Date() + "\n Execution #" + count);
    }
}
