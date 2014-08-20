package research.vn.wcrl.test;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.testng.annotations.Test;

import research.vn.careerservice.service.ICompanyService;
import research.vn.careerservice.vo.Company;
import research.vn.wcrl.CompanyHandler;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CompanyServiceTest extends AbstractBaseContextTest
{
    private static Log4JLogger logger = new Log4JLogger("CompanyServiceTest");
    
    @Autowired
    CompanyHandler companyHandler;
    
    @Test
    public void readCompanyList()
    {
        List<Company> readCompanyList = companyHandler.readCompanyList();
        System.out.println(readCompanyList.toString());
    }
}


/*
 * Changes:
 * $Log: $
 */