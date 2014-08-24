/*
 * JobInfo.java
 *
 */
package research.vn.wcrl.bo;

import java.io.Serializable;


/**
 * The class of job information.
 * 
 * @author hcnlinh
 * @version $Revision: $
 */
public class JobInfo implements Serializable
{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String jobTitle;

    private String jobDescription;

    private String jobRequirement;
    
    private String jobCategory;

    private Integer quantity;

    private String position;

    private String salary;

    private String location;

    private String workingType;
    
    private String workingTime;

    private String contactString;

    private String postDate;

    private String expireDate;

    private String dossierLanguage;

    private CompanyInfo companyInfo;
    
    private String referenceId;

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
     * Gets the quantity
     * 
     * @return Returns the quantity
     */
    public Integer getQuantity()
    {
        return quantity;
    }


    /**
     * Sets the quantity
     * 
     * @param quantity The quantity to set
     */
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }


    /**
     * Gets the position
     * 
     * @return Returns the position
     */
    public String getPosition()
    {
        return position;
    }


    /**
     * Sets the position
     * 
     * @param position The position to set
     */
    public void setPosition(String position)
    {
        this.position = position;
    }


    /**
     * Gets the salary
     * 
     * @return Returns the salary
     */
    public String getSalary()
    {
        return salary;
    }


    /**
     * Sets the salary
     * 
     * @param salary The salary to set
     */
    public void setSalary(String salary)
    {
        this.salary = salary;
    }


    /**
     * Gets the location
     * 
     * @return Returns the location
     */
    public String getLocation()
    {
        return location;
    }


    /**
     * Sets the location
     * 
     * @param location The location to set
     */
    public void setLocation(String location)
    {
        this.location = location;
    }


    /**
     * Gets the workingType
     * 
     * @return Returns the workingType
     */
    public String getWorkingType()
    {
        return workingType;
    }


    /**
     * Sets the workingType
     * 
     * @param workingType The workingType to set
     */
    public void setWorkingType(String workingType)
    {
        this.workingType = workingType;
    }


    /**
     * Gets the contactString
     * 
     * @return Returns the contactString
     */
    public String getContactString()
    {
        return contactString;
    }


    /**
     * Sets the contactString
     * 
     * @param contactString The contactString to set
     */
    public void setContactString(String contactString)
    {
        this.contactString = contactString;
    }


    /**
     * Gets the postDate
     * 
     * @return Returns the postDate
     */
    public String getPostDate()
    {
        return postDate;
    }


    /**
     * Sets the postDate
     * 
     * @param postDate The postDate to set
     */
    public void setPostDate(String postDate)
    {
        this.postDate = postDate;
    }


    /**
     * Gets the expireDate
     * 
     * @return Returns the expireDate
     */
    public String getExpireDate()
    {
        return expireDate;
    }


    /**
     * Sets the expireDate
     * 
     * @param expireDate The expireDate to set
     */
    public void setExpireDate(String expireDate)
    {
        this.expireDate = expireDate;
    }


    /**
     * Gets the dossierLanguage
     * 
     * @return Returns the dossierLanguage
     */
    public String getDossierLanguage()
    {
        return dossierLanguage;
    }


    /**
     * Sets the dossierLanguage
     * 
     * @param dossierLanguage The dossierLanguage to set
     */
    public void setDossierLanguage(String dossierLanguage)
    {
        this.dossierLanguage = dossierLanguage;
    }

    
    /**
     * Sets the workingTime
     *
     * @param workingTime The workingTime to set
     */
    public void setWorkingTime(String workingTime)
    {
        this.workingTime = workingTime;
    }


    /**
     * Gets the workingTime
     *
     * @return Returns the workingTime
     */
    public String getWorkingTime()
    {
        return workingTime;
    }


    /**
     * Sets the jobCategory
     *
     * @param jobCategory The jobCategory to set
     */
    public void setJobCategory(String jobCategory)
    {
        this.jobCategory = jobCategory;
    }


    /**
     * Gets the jobCategory
     *
     * @return Returns the jobCategory
     */
    public String getJobCategory()
    {
        return jobCategory;
    }


    /**
     * Sets the companyInfo
     *
     * @param companyInfo The companyInfo to set
     */
    public void setCompanyInfo(CompanyInfo companyInfo)
    {
        this.companyInfo = companyInfo;
    }


    /**
     * Gets the companyInfo
     *
     * @return Returns the companyInfo
     */
    public CompanyInfo getCompanyInfo()
    {
        return companyInfo;
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getName());
        
        // custome field
        builder.append(", jobTitle=").append(jobTitle);
        builder.append(", position=").append(position);
        builder.append(", salary=").append(salary);
        builder.append(", location=").append(location);
        builder.append(", workingType=").append(workingType);
        builder.append(", contactString=").append(contactString);
        builder.append(", postDate=").append(postDate);
        builder.append(", expireDate=").append(expireDate);
        builder.append(", jobDescription=").append(jobDescription);
        builder.append(", jobRequirement=").append(jobRequirement);
        builder.append(", jobCategory=").append(jobCategory);
        builder.append(", quantity=").append(quantity);
        builder.append(", dossierLanguage=").append(dossierLanguage);
        builder.append(", companyInfo=").append(companyInfo.toString());
        
        builder.append("]");
        return builder.toString();
    }


	public String getReferenceId() {
		return referenceId;
	}


	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
}


/*
 * Changes:
 * $Log: $
 */