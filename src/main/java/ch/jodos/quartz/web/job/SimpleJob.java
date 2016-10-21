package ch.jodos.quartz.web.job;

import java.util.ArrayList;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        List v = new ArrayList();
        for (int i = 0; i <= 5000; i++) {
            byte b[] = new byte[1004857600];
            v.add(b);
        }
    }
}
