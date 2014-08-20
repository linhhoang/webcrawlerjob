/*
 * DataImporter.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import research.vn.wcrl.DataExtractor;
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
                
                IConverter<JobInfo, research.vn.careerservice.vo.Job> jobConverter = new JobConverter();
                research.vn.careerservice.vo.Job jobVo = jobConverter.convert(jobInfo);
                

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