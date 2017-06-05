package exception;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by iceke on 17/6/3.
 * 异常出错重试
 */
public class ExceptionJob implements Job{
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            int zero =0;
            System.out.println("start working~~");
            System.out.println(9527/zero);
        }catch(Exception e){
            System.err.println("job failed");
            JobExecutionException executionException = new JobExecutionException(e);
            //重试该job
            executionException.refireImmediately();
            throw executionException;

        }

    }
}
