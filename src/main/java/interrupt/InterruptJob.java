package interrupt;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by iceke on 17/6/4.
 */
public class InterruptJob implements InterruptableJob {

    private boolean isInterrupt = false;
    private JobKey jobKey = null;
    private static Logger logger= LoggerFactory.getLogger(InterruptJob.class);
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println(jobKey+" is interrupting....");
        isInterrupt = true;

    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("!!!"+jobKey+" is executing at"+ new Date());
        try{
            //工作循环
            for(int i =0;i<400;i++){
                try{
                    Thread.sleep(1000L);
                    //直接跳出
                    if(isInterrupt){
                        System.out.println(jobKey+" is interrupted~~~~");
                        return;
                    }
                }catch (Exception ignore){
                    System.err.println("error in sleep");
                }
            }
            System.out.println("happy");



        }catch (Exception e){
            System.err.println("catch a exception");
        }finally {
            System.out.println(jobKey+" is completed at "+new Date());
        }

    }
}
