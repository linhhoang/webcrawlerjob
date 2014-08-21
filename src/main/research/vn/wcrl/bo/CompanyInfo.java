/*
 * CompanyInfo.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.bo;

import java.io.Serializable;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CompanyInfo implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String companyName;
    
    private String companyAddress;

    private String companyDescription;
    
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
     * Gets the companyDescription
     *
     * @return Returns the companyDescription
     */
    public String getCompanyDescription()
    {
        return companyDescription;
    }

    /**
     * Sets the companyDescription
     *
     * @param companyDescription The companyDescription to set
     */
    public void setCompanyDescription(String companyDescription)
    {
        this.companyDescription = companyDescription;
    }
    

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CompanyInfo [companyName=");
        builder.append(companyName);
        builder.append(", companyAddress=");
        builder.append(companyAddress);
        builder.append(", companyDescription=");
        builder.append(companyDescription);
        builder.append("]");
        return builder.toString();
    }
}


/*
 * Changes:
 * $Log: $
 */