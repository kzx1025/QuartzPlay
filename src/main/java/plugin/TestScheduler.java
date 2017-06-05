package plugin;

import exception.ExceptionJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Properties;

/**
 * Created by iceke on 17/6/5.
 */
public class TestScheduler {
    // First we must get a reference to a scheduler
    public static void main(String args[]) {
        try{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        System.out.println("任务开始执行："+(new Date()).toLocaleString());

        Thread.sleep(100L * 1000L);
        scheduler.shutdown(true);
    } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
