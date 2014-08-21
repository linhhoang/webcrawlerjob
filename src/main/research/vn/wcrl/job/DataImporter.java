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
import research.vn.wcrl.base.converter.IConverter;
import research.vn.wcrl.base.converter.JobConverter;
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
        // TODO: import data from result file to DB
        List<JobInfo> jobInfoList = DataExtractor.extract();
        
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
    }

}


/*
 * Changes:
 * $Log: $
 */