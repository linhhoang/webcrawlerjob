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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
import research.vn.careerservice.service.IReferenceService;
import research.vn.careerservice.utils.ConfigurationUtils;
import research.vn.careerservice.utils.MasterCodeMapUtils;
import research.vn.careerservice.vo.Company;
import research.vn.careerservice.vo.Reference;
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
    
    @Autowired
    IReferenceService referenceService;
    
    /**
     * save job information
     *
     * @param jobInfo
     * @throws CrException
     */
    public void saveJob(JobInfo jobInfo) throws CrException
    {
    	String referenceId = jobInfo.getReferenceId();
    	Reference referenceById = referenceService.selectById(referenceId);
    	if (referenceById != null)
    	{
    		
    		// TODO: in progress
    		CompanyInfo companyInfo = jobInfo.getCompanyInfo();
            
            // save the company information
            Long companyKey = saveCompany(companyInfo);
            
            IConverter<JobInfo, research.vn.careerservice.vo.Job> jobConverter = new JobConverter();
            research.vn.careerservice.vo.Job jobVo = jobConverter.convert(jobInfo);
            
            jobVo.setWorkingType(retreiveWorkingTypeCode(jobInfo.getWorkingType()));
            jobVo.setPosition(retreivePositionCode(jobInfo.getPosition()));
            jobVo.setLocation(retreiveLocationCode(jobInfo.getLocation()));
			jobVo.setDossierLanguage(retrieveDossierLanguageCode(jobInfo.getDossierLanguage()));
			
            jobVo.setCompanyKey(companyKey);
            try
            {
                jobService.insert(jobVo);
                
                Reference updateReference = referenceById.clone();
        		updateReferenceStatus(updateReference);
            } catch (PersistenceException e)
            {
                logger.error("Error occured: " + e.getMessage(), e);
                throw new CrApplicationException(e);
            }
    	}
        
    }


	private Integer retreiveWorkingTypeCode(String workingType) {
		if (StringUtils.isNotEmpty(workingType))
		{
			Map<Integer, String> workingTypeMap = MasterCodeMapUtils.getInstance().getWorkingTypeMap();
			Set<Entry<Integer, String>> entrySet = workingTypeMap.entrySet();
			
			if (!CollectionUtils.isEmpty(entrySet))
			{
				for (Entry<Integer, String> entry : entrySet) {
					if (workingType.equals(entry.getValue()))
					{
						return entry.getKey();
					}
				}
			}
		}
		
		return null;
	}


	private Integer retreivePositionCode(String position) {
		if (StringUtils.isNotEmpty(position))
		{
			Map<Integer, String> positionMap = MasterCodeMapUtils.getInstance().getPositionMap();
			Set<Entry<Integer, String>> entrySet = positionMap.entrySet();
			
			if (!CollectionUtils.isEmpty(entrySet))
			{
				for (Entry<Integer, String> entry : entrySet) {
					if (position.equals(entry.getValue()))
					{
						return entry.getKey();
					}
				}
			}
		}
		
		return null;
	}


	private Integer retreiveLocationCode(String location) {
		if (StringUtils.isNotEmpty(location))
		{
			Map<Integer, String> locationMap = MasterCodeMapUtils.getInstance().getLocationMap();
			Set<Entry<Integer, String>> entrySet = locationMap.entrySet();
			
			if (!CollectionUtils.isEmpty(entrySet))
			{
				for (Entry<Integer, String> entry : entrySet) {
					if (location.equals(entry.getValue()))
					{
						return entry.getKey();
					}
				}
			}
		}
		
		return null;
	}


	private Integer retrieveDossierLanguageCode(String dossierLanguage) {
		if (StringUtils.isNotEmpty(dossierLanguage))
		{
			Map<Integer, String> dossierLanguageMap = MasterCodeMapUtils.getInstance().getDossierLanguageMap();
			Set<Entry<Integer, String>> entrySet = dossierLanguageMap.entrySet();
			
			if (!CollectionUtils.isEmpty(entrySet))
			{
				for (Entry<Integer, String> entry : entrySet) {
					if (dossierLanguage.equals(entry.getValue()))
					{
						return entry.getKey();
					}
				}
			}
		}
		
		return null;
	}


	private void updateReferenceStatus(Reference updateReference) {
		updateReference.setExtractedFlg(true);
		updateReference.setUpdater(0L);
		updateReference.setUpdateDate(new Date());
		referenceService.update(updateReference);
	}


	/**
     * Save company information. If company is existed, return the companyKey, else write new company and return the companyKey
     * @param companyInfo
     * @return
     * @throws CrApplicationException
     */
	private Long saveCompany(CompanyInfo companyInfo)
			throws CrApplicationException {
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

                companyKey = companyService.insert(newCompany);
                
            }
        } catch (PersistenceException e)
        {
            logger.error("Error occured: " + e.getMessage(), e);
            throw new CrApplicationException(e);
        }
		return companyKey;
	}
}


/*
 * Changes:
 * $Log: $
 */