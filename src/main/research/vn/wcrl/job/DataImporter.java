/*
 * DataImporter.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.job;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import research.vn.careerservice.base.exception.CrException;
import research.vn.wcrl.DataExtractor;
import research.vn.wcrl.JobPersistence;
import research.vn.wcrl.bo.JobInfo;
import research.vn.wcrl.utils.ContextLoader;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class DataImporter implements Job
{
    private static Log4JLogger log = new Log4JLogger("JobPersistence");
    
    /**
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
    	log.info("Start dataImporter job.");
    	
    	log.info("Extracting job information..");
        List<JobInfo> jobInfoList = DataExtractor.extract("VNWs"); // TODO: setting siteCode
        
        if (jobInfoList != null && jobInfoList.size() > 0)
        {
            for (JobInfo jobInfo : jobInfoList)
            {
                preprocessingData(jobInfo);
                
                JobPersistence jobPersistence = ContextLoader.getInstance().getBean(JobPersistence.class);
                
                try
                {
                    jobPersistence.saveJob(jobInfo);
                }
                catch (CrException e)
                {
                    log.error("Error occurred: " + e.getMessage(), e);
                }
            }
        }
        
    }


    /**
     * preprocessing data
     *
     * @param jobInfo
     */
    private void preprocessingData(JobInfo jobInfo)
    {
    	log.info("preprocessing data..");
    	// TODO: TBD
    }

}


/*
 * Changes:
 * $Log: $
 */