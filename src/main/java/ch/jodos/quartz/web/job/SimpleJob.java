package ch.jodos.quartz.web.job;

import java.util.ArrayList;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        List v = new ArrayList();
        for (int i = 0; i <= 5; i++) {
            byte b[] = new byte[12];
            v.add(b);
        }
    }
}
