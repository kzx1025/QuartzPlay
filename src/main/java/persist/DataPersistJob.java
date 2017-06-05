package persist;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by iceke on 17/6/1.
 * 测试持久化数据的Job
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DataPersistJob implements Job{
    private int count =1;
    private ArrayList<String> states;
    public static final String COUNT = "count";
    public static final String STATES = "states";


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        int num = dataMap.getInt(COUNT);
        ArrayList<String> tempStates = (ArrayList<String>) dataMap.get(STATES);
        num++;
        tempStates.add(jobKey.toString()+num);
        System.out.println(jobKey+" count is"+count+", num is "+num+", states is "+tempStates);
        dataMap.put(COUNT,num);
        dataMap.put(STATES,tempStates);

        //这个是保存不了的
        count++;


    }
}
