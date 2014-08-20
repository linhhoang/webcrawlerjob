/*
 * DataExtractController.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import research.vn.wcrl.bo.JobInfo;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class DataExtractor
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(DataExtractor.class);
    
    
    /**
     * extract data from result file
     *
     * @return JobInfo
     */
    public static List<JobInfo> extract()
    {
        // TODO: 
        String resultFolder = String.format("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\%1$s\\result", "VNWs");
        String resultFile = resultFolder + File.separator + "res_VNWs_1000usdmonth491741jd.xml";
        
        JobInfo jobInfo = JobParser.getInstance().parse(resultFile);
        
        List<JobInfo> jobInfoList = new ArrayList<JobInfo>();
        jobInfoList.add(jobInfo);
        return jobInfoList;
    }
    
    
}


/*
 * Changes:
 * $Log: $
 */