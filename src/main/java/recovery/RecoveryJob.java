package recovery;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by iceke on 17/6/5.
 */
public class RecoveryJob implements Job{
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        for(int i = 0;i<5;i++){
            try{
                System.out.println("hello world is running at "+new Date());
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
