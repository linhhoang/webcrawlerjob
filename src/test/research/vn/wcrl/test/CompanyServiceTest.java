package research.vn.wcrl.test;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.testng.annotations.Test;

import research.vn.careerservice.bo.CompanyFilter;
import research.vn.careerservice.service.ICompanyService;
import research.vn.careerservice.vo.Company;
import research.vn.wcrl.utils.ContextLoader;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CompanyServiceTest
{
    private static Log4JLogger logger = new Log4JLogger("CompanyServiceTest");
    
    public static void main(String[] args)
    {
        ICompanyService companyService = (ICompanyService)ContextLoader.getInstance().getBean("companyService");
        List<Company> companyList = companyService.list();
        
        if (!CollectionUtils.isEmpty(companyList))
        {
            for (Company company : companyList)
            {
                logger.info("company :" + company.getCompanyName());
            }
        }
    }
}


/*
 * Changes:
 * $Log: $
 */