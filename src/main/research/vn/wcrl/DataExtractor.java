/*
 * DataExtractController.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import research.vn.wcrl.bo.JobInfo;
import research.vn.wcrl.utils.PropertyUtils;

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
        String resultFolder = PropertyUtils.getInstance().getValue("output.folder.result");
        log.debug("Extract data from folder '" + resultFolder + "'");
        File resultFolderFile = new File(resultFolder);
        if (resultFolderFile.isDirectory())
        {
        	File[] listFiles = resultFolderFile.listFiles();
        	
        	if (ArrayUtils.isNotEmpty(listFiles))
        	{
        		List<JobInfo> jobInfoList = new ArrayList<JobInfo>();
        		for (File file : listFiles) {
        			String resultFile = resultFolder + File.separator + file.getName();
        			 log.debug("Parse job info from reslult file '" + file.getName() + "'");
        	        JobInfo jobInfo = JobParser.getInstance().parse(resultFile);
        	        
        	        jobInfoList.add(jobInfo);
        		}
        		
        		return jobInfoList;
        	}
        }
        
        return null;
    }
    
    
}


/*
 * Changes:
 * $Log: $
 */