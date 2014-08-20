/*
 * JobConverter.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.base.converter;

import org.springframework.stereotype.Component;

import research.vn.careerservice.vo.Job;
import research.vn.wcrl.bo.JobInfo;

/**
 * The converter of Job data
 *
 * @author hcnlinh
 * @version $Revision:  $
 * 
 */
@Component
public class JobConverter extends AbstractConverter<JobInfo, Job>
{

    /**
     * @see research.vn.wcrl.base.converter.IConverter#convert(java.lang.Object)
     */
    @Override
    public Job convertImpl(JobInfo jobInfo)
    {
        Job job = new Job();
        job.setJobTitle(jobInfo.getJobTitle());
        job.setJobSpecification(jobInfo.getJobDescription());
        job.setJobRequirement(jobInfo.getJobRequirement());
        job.setQuantity(jobInfo.getQuantity());
        job.setSalary(jobInfo.getSalary());
        job.setContactString(jobInfo.getContactString());

        return job;
    }

}


/*
 * Changes:
 * $Log: $
 */