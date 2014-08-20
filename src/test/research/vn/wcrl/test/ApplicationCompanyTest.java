/*
 * ApplicationCompanyTest.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.test;

import java.util.List;

import research.vn.careerservice.vo.Company;
import research.vn.wcrl.CompanyHandler;
import research.vn.wcrl.utils.ContextLoader;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class ApplicationCompanyTest
{

    public static void main(String[] args)
    {
        CompanyHandler companyHandler = ContextLoader.getInstance().getBean(CompanyHandler.class);
        
        List<Company> companyList = companyHandler.readCompanyList();
        
        if (companyList != null)
        {
            System.out.println(companyList.toString());
        }
    }
}


/*
 * Changes:
 * $Log: $
 */