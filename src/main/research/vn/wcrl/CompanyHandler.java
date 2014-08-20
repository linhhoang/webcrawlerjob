/*
 * CompanyHandler.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import research.vn.careerservice.service.ICompanyService;
import research.vn.careerservice.vo.Company;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
@Component
public class CompanyHandler
{
    private static Log4JLogger logger = new Log4JLogger("CompanyHandler");
    
    @Autowired
    ICompanyService companyService;
    
    public List<Company> readCompanyList()
    {
        List<Company> companyList = companyService.list();
        
        if (!CollectionUtils.isEmpty(companyList))
        {
            for (Company company : companyList)
            {
                logger.info("company :" + company.getCompanyName());
            }
        }
        return companyList;
    }
}


/*
 * Changes:
 * $Log: $
 */