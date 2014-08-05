/*
 * CrawlerLauncher.java
 *
 */
package research.vn.wcrl.job;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CrawlerLauncher
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(CrawlerLauncher.class);
    
    /**
     * the main method
     *
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            SchedulerFactory factory = new StdSchedulerFactory("job/quartz.properties");
            Scheduler scheduler = factory.getScheduler();
            log.info("start scheduler");
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CrawlerJob", "webcrawlerGroup").startNow().build();
//            scheduler.scheduleJob(trigger);
            scheduler.start();
        }
        catch (SchedulerException e)
        {
            log.warn("Error occurred: " + e.getMessage(), e);
        }
    }
}


/*
 * Changes:
 * $Log: $
 */