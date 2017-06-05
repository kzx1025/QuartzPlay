package cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by iceke on 17/5/31.
 * 测试Cron表达式
 */
public class CronJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("测试Quartz"+new Date());
    }
}
