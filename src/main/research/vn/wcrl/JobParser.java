/*
 * JobParser.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import research.vn.wcrl.bo.JobInfo;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class JobParser
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(JobParser.class);
    
    private static JobParser instance;
    
    public static JobParser getInstance()
    {
        if (instance == null)
        {
            instance = new JobParser();
        }
        
        return instance;
    }
    
    public JobInfo parse(String filePath)
    {
        JobParserHandler jobParser = new JobParserHandler();
        
        // Create SAX 2 parser...
        XMLReader xr;
        try
        {
            xr = XMLReaderFactory.createXMLReader();
            
            // Set the ContentHandler...
            xr.setContentHandler(jobParser);

            // Parse the file...
            
            xr.parse(new InputSource(new FileReader(filePath)));
        }
        catch (SAXException e)
        {
            log.error("Error occurred: " + e.getMessage(), e);
        }
        catch (FileNotFoundException e)
        {
            log.error("Error occurred: " + e.getMessage(), e);
        }
        catch (IOException e)
        {
            log.error("Error occurred: " + e.getMessage(), e);
        }
        
        return jobParser.getJobInfo();
    }
}


/*
 * Changes:
 * $Log: $
 */