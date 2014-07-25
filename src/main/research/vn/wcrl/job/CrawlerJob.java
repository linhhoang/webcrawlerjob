/*
 * CrawlerJob.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import research.vn.wcrl.CrawlerController;

/**
 * The class implement Job for crawling
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CrawlerJob implements Job
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(CrawlerJob.class);
    
    /**
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        log.info("excute crawl job.");
        CrawlerController.crawl();
    }

}


/*
 * Changes:
 * $Log: $
 */