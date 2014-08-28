/*
 * DataImporterTest.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.test;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

import research.vn.wcrl.job.DataImporter;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class DataImporterTest
{
    private static Logger logger = Logger.getLogger("DataImporterTest");
    
    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args)
    {
        DataImporter dataImporter = new DataImporter();
        try
        {
            dataImporter.execute(null);
        }
        catch (JobExecutionException e)
        {
            logger.warn("Error occurred: " + e.getMessage(), e);
        }
    }
}


/*
 * Changes:
 * $Log: $
 */