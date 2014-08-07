/*
 * WrapperTest.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import research.vn.careerservice.service.ISourceService;
import research.vn.careerservice.service.IWrapperService;
import research.vn.careerservice.vo.Source;
import research.vn.careerservice.vo.Wrapper;
import research.vn.wcrl.utils.ContextLoader;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class WrapperTest
{
    private static Logger logger = Logger.getLogger("WrapperTest");
    private static IWrapperService wrapperService = (IWrapperService)ContextLoader.getInstance().getBean("wrapperService");
    private static ISourceService sourceService = (ISourceService)ContextLoader.getInstance().getBean("sourceService");
    
    public static void main(String[] args)
    {
        System.out.println("============== Write wrapper to DB ==============");
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Input site code:");
        String siteName = sc.nextLine();
        
        if (siteName == null || siteName.length() == 0)
        {
            siteName = "VNWs";
            System.out.println("Sitename default:'" + siteName + "'");
        }
        
        byte[] fileContent = getFileContent(String.format("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\%1$s\\%1$s00.xml", siteName));
        
        String wrapperName = siteName + "00.xml";
        Wrapper wrapper = wrapperService.selectByName(wrapperName);
        
        if (wrapper != null)
        {
            wrapper.setContent(new String(fileContent));
            wrapper.setUpdater(0L);
            wrapper.setUpdateDate(new Date());
            wrapperService.update(wrapper);
        }
        else
        {
            insertWrapper(siteName, fileContent);
        }
        System.out.println("============== Finish ==============");
    }

    
    /**
     * get file content
     *
     * @param filePath
     * @return file content
     */
    private static byte[] getFileContent(String filePath)
    {
        File file = new File(filePath);
        FileInputStream fis;
        byte[] fileContent = null;
        try
        {
            fis = new FileInputStream(file);
            fileContent = new byte[(int)file.length()];
            fis.read(fileContent);
            
            
        }
        catch (FileNotFoundException e1)
        {
            logger.error("Error occurred: " + e1.getMessage(), e1);
        }
        catch (IOException e)
        {
            logger.error("Error occurred: " + e.getMessage(), e);
        }
        return fileContent;
    }

    /**
     * Method description
     *
     * @param siteName
     * @param fileContent
     */
    private static void insertWrapper(String siteName, byte[] fileContent)
    {
        Source source = sourceService.selectById(siteName);

        if (source == null)
        {
            logger.error("Cannot read Source of '" + siteName + "'");
            return;
        }

        Wrapper wrapperInsert = new Wrapper();
        wrapperInsert.setCreator(0L);
        wrapperInsert.setSourceKey(source.getKey());
        wrapperInsert.setCreateDate(new Date());
        wrapperInsert.setWrapperName(siteName + "00.xml");
        wrapperInsert.setDeleteFlg(false);
        wrapperInsert.setContent(new String(fileContent));
        wrapperService.insert(wrapperInsert);
    }
    
}


/*
 * Changes:
 * $Log: $
 */