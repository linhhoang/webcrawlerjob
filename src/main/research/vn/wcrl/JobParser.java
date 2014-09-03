/*
 * JobParser.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
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
            InputSource inputSource = new InputSource(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            xr.parse(inputSource);
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
        JobInfo jobInfo = jobParser.getJobInfo();
        jobInfo.setReferenceId(getReferenceId(filePath));
        return jobInfo;
    }

    /**
     * Get reference id from file path
     * @param filePath
     * @return
     */
	private String getReferenceId(String filePath) {
		
		log.debug("Get referenceId of '" + filePath + "'");
		if (StringUtils.isNotEmpty(filePath))
		{
			Pattern pattern = Pattern.compile("(.*_)(.*)(_)");
			Matcher matcher = pattern.matcher(filePath);
			
			if (matcher.find())
			{
				return matcher.group(2);
			}

		}
		
		return null;
	}
}


/*
 * Changes:
 * $Log: $
 */