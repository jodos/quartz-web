package ch.jodos.quartz.web.servlet;

import ch.jodos.quartz.web.job.SimpleJob;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzServlet extends HttpServlet {

    Scheduler quartzScheduler;

    @Override
    public void init(ServletConfig cfg) {
        try {
            String key = "org.quartz.impl.StdSchedulerFactory.KEY";
            ServletContext servletContext = cfg.getServletContext();
            StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute(key);
            quartzScheduler = factory.getScheduler();

        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            for (int i = 0; i < 1000; i++) {
                JobDetail job = newJob(SimpleJob.class).withIdentity("job_" + i, "group").requestRecovery(true).build();
                Trigger trigger = newTrigger().withIdentity("trigger_" + i, "group").startNow().withSchedule(simpleSchedule().withIntervalInMilliseconds(2000L).repeatForever()).build();
                quartzScheduler.scheduleJob(job, trigger);
            }
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
