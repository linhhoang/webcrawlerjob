/*
 * Example.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.io.CharArrayWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import research.vn.wcrl.bo.CompanyInfo;
import research.vn.wcrl.bo.JobInfo;
import research.vn.wcrl.utils.Constants;


/**
 * @author hcnlinh
 * @version $Revision: $
 */
public class JobParserHandler extends DefaultHandler
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(JobParserHandler.class);
    private CharArrayWriter field = new CharArrayWriter();
    private CharArrayWriter contents = new CharArrayWriter();
    
    private JobInfo jobInfo;
    
    /**
     * Constructor
     *
     */
    public JobParserHandler()
    {
        jobInfo = new JobInfo();
    }
    
    
    /**
     * Sets the jobInfo
     *
     * @param jobInfo The jobInfo to set
     */
    public void setJobInfo(JobInfo jobInfo)
    {
        this.jobInfo = jobInfo;
    }

    /**
     * Gets the jobInfo
     *
     * @return Returns the jobInfo
     */
    public JobInfo getJobInfo()
    {
        return jobInfo;
    }

    
    /**
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException
    {
        log.debug("SAX Event: START DOCUMENT");
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException
    {
        log.debug("SAX Event: END DOCUMENT");
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
        throws SAXException
    {
        field.reset();
        contents.reset();
        log.debug("SAX Event: START ELEMENT[ " + localName + " ]");
        
        if (Constants.ATTRIBUTE.equalsIgnoreCase(localName))
        {
            try
            {
                // Also, let's print the attributes if
                // there are any...
                for (int i = 0; i < attr.getLength(); i++)
                {
                    if (Constants.LABEL.equalsIgnoreCase(attr.getLocalName(i)))
                    {
                        if (Constants.F_JOB_TITLE.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_JOB_DESCRIPTION.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_JOB_REQUIREMENT.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_JOB_CATEGORY.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_POSITION.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_WORKING_TIME.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_SALARY.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_QUANTITY.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_WORKING_TYPE.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_CONTACT_STRING.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_LOCATION.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_POST_DATE.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_EXPIRE_DATE.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_DOSSIER_LANGUAGE.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_COMPANY_NAME.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_COMPANY_ADDRESS.equalsIgnoreCase(attr.getValue(i))
                                || Constants.F_COMPANY_DESCRIPTION.equalsIgnoreCase(attr.getValue(i))
                                )
                        {
                            field.write(attr.getValue(i));
                        }
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException
    {
        if (contents == null)
        {
            return;
        }
        
        String contentTrimed = contents.toString().trim();
        if (Constants.F_JOB_TITLE.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setJobTitle(contentTrimed);
        }
        else if (Constants.F_JOB_DESCRIPTION.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setJobDescription(contentTrimed);
        }
        else if (Constants.F_JOB_REQUIREMENT.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setJobRequirement(contentTrimed);
        }
        else if (Constants.F_JOB_CATEGORY.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setJobCategory(contentTrimed);
        }
        else if (Constants.F_POSITION.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setPosition(contentTrimed);
        }
        else if (Constants.F_WORKING_TIME.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setWorkingTime(contentTrimed);
        }
        else if (Constants.F_WORKING_TYPE.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setWorkingType(contentTrimed);
        }
        else if (Constants.F_SALARY.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setSalary(contentTrimed);
        }
        else if (Constants.F_QUANTITY.equalsIgnoreCase(field.toString()))
        {
            String quantityStr = contentTrimed;
            Integer parseInt = null;
            try
            {
                
                parseInt = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e)
            {
                log.warn("Error occured: " + e.getMessage());
                // nop
            }
            jobInfo.setQuantity(parseInt);
        }
        else if (Constants.F_LOCATION.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setLocation(contentTrimed);
        }
        else if (Constants.F_CONTACT_STRING.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setContactString(contentTrimed);
        }
        else if (Constants.F_POST_DATE.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setPostDate(contentTrimed);
        }
        else if (Constants.F_EXPIRE_DATE.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setExpireDate(contentTrimed);
        }
        else if (Constants.F_DOSSIER_LANGUAGE.equalsIgnoreCase(field.toString()))
        {
            jobInfo.setDossierLanguage(contentTrimed);
        }
        else if (Constants.F_COMPANY_NAME.equalsIgnoreCase(field.toString()))
        {
            if (jobInfo.getCompanyInfo() == null)
            {
                jobInfo.setCompanyInfo(new CompanyInfo());
            }
            jobInfo.getCompanyInfo().setCompanyName(contentTrimed);
        }
        else if (Constants.F_COMPANY_ADDRESS.equalsIgnoreCase(field.toString()))
        {
            if (jobInfo.getCompanyInfo() == null)
            {
                jobInfo.setCompanyInfo(new CompanyInfo());
            }
            jobInfo.getCompanyInfo().setCompanyAddress(contentTrimed);
        }
        else if (Constants.F_COMPANY_DESCRIPTION.equalsIgnoreCase(field.toString()))
        {
            if (jobInfo.getCompanyInfo() == null)
            {
                jobInfo.setCompanyInfo(new CompanyInfo());
            }
            jobInfo.getCompanyInfo().setCompanyDescription(contentTrimed);
        }
        
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        contents.write(ch, start, length);
    }

}

/*
 * Changes: $Log: $
 */