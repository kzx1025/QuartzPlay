package plugin;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;
import java.util.Set;

/**
 * Created by iceke on 17/6/5.
 */
public class PluginJob implements Job {

    private JobKey jobkey=null;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        jobkey = context.getJobDetail().getKey();

        System.out.println("Excuting job: " + jobkey + " executing at " + new Date()+" fire by: "+context.getTrigger().getKey());

       /** if(context.getMergedJobDataMap().size()>0){
            Set<String> keys=  context.getMergedJobDataMap().keySet();
            for (String key : keys) {
                String value= context.getMergedJobDataMap().getString(key);
                System.out.println(" jobdatamap entry: "+key+" = "+value);
            }
            context.setResult("hello");
        }**/


    }
}
