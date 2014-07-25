/*
 * JobInfo.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.bo;

import java.io.Serializable;

/**
 * The class of job information.
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class JobInfo implements Serializable
{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String jobTitle;
    
    private String companyName;
    
    private String companyAddress;
    
    private String jobDescription;
    
    private String jobRequirement;
    
    /**
     * Gets the jobTitle
     *
     * @return Returns the jobTitle
     */
    public String getJobTitle()
    {
        return jobTitle;
    }
    /**
     * Sets the jobTitle
     *
     * @param jobTitle The jobTitle to set
     */
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }
    /**
     * Gets the companyName
     *
     * @return Returns the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
    /**
     * Sets the companyName
     *
     * @param companyName The companyName to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    /**
     * Gets the companyAddress
     *
     * @return Returns the companyAddress
     */
    public String getCompanyAddress()
    {
        return companyAddress;
    }
    /**
     * Sets the companyAddress
     *
     * @param companyAddress The companyAddress to set
     */
    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }
    /**
     * Gets the jobDescription
     *
     * @return Returns the jobDescription
     */
    public String getJobDescription()
    {
        return jobDescription;
    }
    /**
     * Sets the jobDescription
     *
     * @param jobDescription The jobDescription to set
     */
    public void setJobDescription(String jobDescription)
    {
        this.jobDescription = jobDescription;
    }
    /**
     * Gets the jobRequirement
     *
     * @return Returns the jobRequirement
     */
    public String getJobRequirement()
    {
        return jobRequirement;
    }
    /**
     * Sets the jobRequirement
     *
     * @param jobRequirement The jobRequirement to set
     */
    public void setJobRequirement(String jobRequirement)
    {
        this.jobRequirement = jobRequirement;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        final StringBuffer stAttr = new StringBuffer("': '");
        final StringBuffer edAttr = new StringBuffer("'");
        final StringBuffer inAttr = new StringBuffer("  ");
        final StringBuffer nl = new StringBuffer("\n");

        StringBuffer buffer = new StringBuffer();
        buffer.append("Job info:" + nl);
        buffer.append(inAttr).append("jobTitle").append(stAttr).
            append(jobTitle).append(edAttr).append(nl);
        buffer.append(inAttr).append("companyName").append(stAttr).
            append(companyName).append(edAttr).append(nl);
        buffer.append(inAttr).append("companyAddress").append(stAttr).
            append(companyAddress).append(edAttr).append(nl);
        buffer.append(inAttr).append("jobDescription").append(stAttr).
            append(jobDescription).append(edAttr).append(nl);
        buffer.append(inAttr).append("jobRequirement").append(stAttr).
            append(jobRequirement).append(edAttr).append(nl);
        buffer.append(super.toString());
        return buffer.toString();
    }
}


/*
 * Changes:
 * $Log: $
 */