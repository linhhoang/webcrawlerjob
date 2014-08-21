/*
 * CompanyHandler.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import research.vn.careerservice.base.exception.CrApplicationException;
import research.vn.careerservice.base.exception.CrException;
import research.vn.careerservice.bo.CompanyFilter;
import research.vn.careerservice.service.ICompanyService;
import research.vn.careerservice.service.IJobService;
import research.vn.careerservice.vo.Company;
import research.vn.wcrl.base.converter.IConverter;
import research.vn.wcrl.base.converter.JobConverter;
import research.vn.wcrl.bo.CompanyInfo;
import research.vn.wcrl.bo.JobInfo;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
@Component
public class JobPersistence
{
    private static Log4JLogger logger = new Log4JLogger("JobPersistence");
    
    @Autowired
    IJobService jobService;
    
    @Autowired
    ICompanyService companyService;
    
    /**
     * save job information
     *
     * @param jobInfo
     * @throws CrException
     */
    public void saveJob(JobInfo jobInfo) throws CrException
    {
        CompanyInfo companyInfo = jobInfo.getCompanyInfo();
        
        Long companyKey = null;
        CompanyFilter companyfilter = new CompanyFilter();
        companyfilter.setCompanyName(companyInfo.getCompanyName());
        companyfilter.setCompanyAddress(companyInfo.getCompanyAddress());
        try
        {
            List<Company> companyList = companyService.search(companyfilter);
            if (!CollectionUtils.isEmpty(companyList))
            {
                Company company = companyList.get(0);
                companyKey = company.getKey();
            }
            else
            {
                Company newCompany = new Company();
                newCompany.setCompanyName(companyInfo.getCompanyName());
                newCompany.setCompanyAddress(companyInfo.getCompanyAddress());
                newCompany.setCompanyDescription(companyInfo.getCompanyDescription());
                newCompany.setCreateDate(new Date());
                newCompany.setCreator(0L);

                companyService.insert(newCompany);
                
            }
        } catch (PersistenceException e)
        {
            logger.error("Error occured: " + e.getMessage(), e);
            throw new CrApplicationException(e);
        }
        
        
        IConverter<JobInfo, research.vn.careerservice.vo.Job> jobConverter = new JobConverter();
        research.vn.careerservice.vo.Job jobVo = jobConverter.convert(jobInfo);
        
        try
        {
            jobService.insert(jobVo);
        } catch (PersistenceException e)
        {
            logger.error("Error occured: " + e.getMessage(), e);
            throw new CrApplicationException(e);
        }
    }
}


/*
 * Changes:
 * $Log: $
 */