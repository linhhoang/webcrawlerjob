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
import org.testng.Assert;

import research.vn.careerservice.base.exception.CrApplicationException;
import research.vn.careerservice.base.exception.CrException;
import research.vn.careerservice.bo.CompanyFilter;
import research.vn.careerservice.bo.MasterCodeFilter;
import research.vn.careerservice.bo.MasterCodePK;
import research.vn.careerservice.service.ICompanyService;
import research.vn.careerservice.service.IJobService;
import research.vn.careerservice.service.IMasterCodeService;
import research.vn.careerservice.service.IReferenceService;
import research.vn.careerservice.utils.ConfigurationUtils;
import research.vn.careerservice.vo.Company;
import research.vn.careerservice.vo.MasterCode;
import research.vn.careerservice.vo.Reference;
import research.vn.wcrl.base.converter.IConverter;
import research.vn.wcrl.base.converter.JobConverter;
import research.vn.wcrl.bo.CompanyInfo;
import research.vn.wcrl.bo.JobInfo;
import research.vn.careerservice.base.common.Constants;

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
    private IJobService jobService;
    
    @Autowired
    private ICompanyService companyService;
    
    @Autowired
    private IReferenceService referenceService;
    
    @Autowired
    private IMasterCodeService masterCodeService;
    
    /**
     * save job information
     *
     * @param jobInfo
     * @throws CrException
     */
    public void saveJob(JobInfo jobInfo) throws CrException
    {
    	logger.info("Start saving job information.");
    	String referenceId = jobInfo.getReferenceId();
    	Reference referenceById = referenceService.selectById(referenceId);
    	if (referenceById != null)
    	{
    		CompanyInfo companyInfo = jobInfo.getCompanyInfo();
            
            // save the company information
            Long companyKey = saveCompany(companyInfo);
            
            IConverter<JobInfo, research.vn.careerservice.vo.Job> jobConverter = new JobConverter();
            research.vn.careerservice.vo.Job jobVo = jobConverter.convert(jobInfo);
            
            jobVo.setWorkingType(retreiveWorkingTypeCode(jobInfo.getWorkingType()));
            jobVo.setPosition(retreivePositionCode(jobInfo.getPosition()));
            jobVo.setLocation(retreiveLocationCode(jobInfo.getLocation()));
			jobVo.setDossierLanguage(retrieveDossierLanguageCode(jobInfo.getDossierLanguage()));
			jobVo.setReferenceKey(referenceById.getKey());
            jobVo.setCompanyKey(companyKey);
            jobVo.setCreator(0L);
            try
            {
                jobService.insert(jobVo);
                logger.info(String.format("The job '%s' is saved successful.", jobInfo.getJobTitle()));
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
			Map<Integer, String> workingTypeMap = ConfigurationUtils.getInstance().getWorkingTypeMap();
			
			if (workingTypeMap != null)
			{
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
			addNewMasterCode(workingType, Constants.WORKINGTYPE_MC, Constants.COMMON_FK);
			logger.warn(String.format("The workingType '%s' is not exist in MasterCode", workingType));
		}
		else
		{
			logger.warn("The workingType '%s' is null or empty");
		}
		
		return null;
	}


    /**
     * add new value to MasterCode
     *
     * @param value
     * @param keyValue 
     * @param functionKey 
     */
    void addNewMasterCode(String value, String keyValue, Long functionKey)
    {
        Long selectMaxSequence = masterCodeService.selectMaxSequence(new MasterCodePK(keyValue, null, functionKey));
        
        if (selectMaxSequence == null)
        {
            selectMaxSequence = -1L;
        }
        addMasterCode(keyValue, selectMaxSequence.intValue() + 1, functionKey, value, value);
    }

    
    /**
     * add company
     * @param keyValue 
     * @param sequence 
     * @param functionKey 
     * @param description 
     * @param values 
     *
     */
    private void addMasterCode(String keyValue,
                            Integer sequence,
                            Long functionKey,
                            String description,
                            String... values)
    {
		MasterCodeFilter masterCodeFilter = new MasterCodeFilter();
		masterCodeFilter.setKeyValue(keyValue);
		masterCodeFilter.setFunctionKey(functionKey);
		if (values.length > 0)
        {
			masterCodeFilter.setValue1(values[0]);
        }
		
		List<MasterCode> masterCodeList = masterCodeService.search(masterCodeFilter);
		if (CollectionUtils.isEmpty(masterCodeList))
		{
			MasterCode masterCode = new MasterCode();
	        masterCode.setKeyValue(keyValue);
	        masterCode.setSequence(sequence);
	        masterCode.setFunctionKey(functionKey);
	        masterCode.setDescription(description);
	        if (values != null)
	        {
	            if (values.length > 0)
	            {
	                masterCode.setValue1(values[0]);
	            }
	            if (values.length > 1)
	            {
	                masterCode.setValue1(values[1]);
	            }
	            if (values.length > 2)
	            {
	                masterCode.setValue1(values[2]);
	            }
	            if (values.length > 3)
	            {
	                masterCode.setValue1(values[3]);
	            }
	            if (values.length > 4)
	            {
	                masterCode.setValue1(values[4]);
	            }
	        }
	        
	        masterCode.setCreator(0L);
	        masterCode.setCreateDate(new Date());
	        masterCode.setDeleteFlg(false);
	        
	        try
	        {
	            masterCodeService.insert(masterCode);
	        }
	        catch (Exception e)
	        {
	            Assert.fail("Cannot create mastercode '" + keyValue + "'");
	        }
		}
    }
    

	private Integer retreivePositionCode(String position) {
		if (StringUtils.isNotEmpty(position))
		{
			Map<Integer, String> positionMap = ConfigurationUtils.getInstance().getPositionMap();
			if (positionMap != null)
			{
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
			logger.warn(String.format("The position '%s' is not exist in MasterCode", position));
			
			addNewMasterCode(position, Constants.POSITION_MC, Constants.COMMON_FK);
		}
		else
		{
			logger.warn("The position '%s' is null or empty");
		}
		return null;
	}


	private Integer retreiveLocationCode(String location) {
		if (StringUtils.isNotEmpty(location))
		{
			Map<Integer, String> locationMap = ConfigurationUtils.getInstance().getLocationMap();
			if (locationMap != null)
			{
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
			logger.warn(String.format("The location '%s' is not exist in MasterCode", location));
			addNewMasterCode(location, Constants.LOCATION_MC, Constants.COMMON_FK);
		}
		else
		{
			logger.warn("The location '%s' is null or empty");
		}
		return null;
	}


	private Integer retrieveDossierLanguageCode(String dossierLanguage) {
		if (StringUtils.isNotEmpty(dossierLanguage))
		{
			Map<Integer, String> dossierLanguageMap = ConfigurationUtils.getInstance().getDossierLanguageMap();
			if (dossierLanguageMap != null)
			{
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
			logger.warn(String.format("The dossierLanguage '%s' is not exist in MasterCode", dossierLanguage));
			addNewMasterCode(dossierLanguage, Constants.DOSSIERLANGUAGE_MC, Constants.COMMON_FK);
		}
		else
		{
			logger.warn("The dossierLanguage '%s' is null or empty");
		}
		return null;
	}


	private void updateReferenceStatus(Reference updateReference) {
		updateReference.setExtractedFlg(true);
		updateReference.setUpdater(0L);
		updateReference.setUpdateDate(new Date());
		referenceService.update(updateReference);
		logger.info("The reference status has been update to 'EXTRACTED'");
	}


	/**
     * Save company information. If company is existed, return the companyKey, else write new company and return the companyKey
     * @param companyInfo
     * @return CompanyKey
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
				logger.info(String.format(
						"The company '%s' has been created successful.",
						companyInfo.getCompanyName()));
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