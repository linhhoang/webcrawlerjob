/*
 * XMLLoader.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Date;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import research.vn.careerservice.service.JobService;
import research.vn.careerservice.vo.Job;
import research.vn.wcrl.JobParserHandler;
import research.vn.wcrl.bo.JobInfo;


/**
 * @author hcnlinh
 * @version $Revision: $
 */
public class XMLLoader
{
    private static final Logger log = Logger.getLogger(XMLLoader.class);
    
    private static JobService jobService = (JobService)ContextLoader.getInstance().getBean("jobService");
    
    /**
     * Method description
     *
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Example1 SAX Events:");
        try
        {
            // Create SAX 2 parser...
            XMLReader xr = XMLReaderFactory.createXMLReader();
            JobParserHandler jobParser = new JobParserHandler();
            // Set the ContentHandler...
            xr.setContentHandler(jobParser);
            String resultFolder = String.format("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\%1$s\\result", "VNWs");
            // Parse the file...
            xr.parse(new InputSource(new FileReader(resultFolder + File.separator + "res_VNWs_1000usdmonth491741jd.xml")));
            
            JobInfo jobInfo = jobParser.getJobInfo();
            
            
            jobService.insert(convertJob(jobInfo));
            log.debug("VALUES: " + jobInfo.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * convert Job to JobInfo
     *
     * @param jobInfo
     * @return
     */
    private static Job convertJob(JobInfo jobInfo)
    {
        if (jobInfo == null)
        {
            return null;
        }
        Job job = new Job();
        job.setJobTitle(jobInfo.getJobTitle());
        job.setJobSpecification(jobInfo.getJobDescription());
        job.setJobRequirement(jobInfo.getJobRequirement());
        job.setQuantity(jobInfo.getQuantity());
//        job.setPosition(jobInfo.getPosition());
        job.setSalary(jobInfo.getSalary());
//        job.setLocation(jobInfo.getLocation());
//        job.setWorkingType(jobInfo.getWorkingType());
//        job.setCompanyKey(jobInfo.getCompanyKey());
        job.setContactString(jobInfo.getContactString());
        job.setPostDate(new Date()); // TODO: parse posted date
//        job.setExpireDate(jobInfo.getExpireDate());
//        job.setDossierLanguage(jobInfo.getDossierLanguage());
//        job.setReferenceKey(jobInfo.getReferenceKey());
        
        return job;
    }
}

/*
 * Changes: $Log: $
 */