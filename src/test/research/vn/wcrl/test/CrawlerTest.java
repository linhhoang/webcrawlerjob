package research.vn.wcrl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.sleepycat.je.log.FileReader;

import research.vn.careerservice.service.ISourceService;
import research.vn.careerservice.service.IWrapperService;
import research.vn.careerservice.vo.Source;
import research.vn.careerservice.vo.Wrapper;
import research.vn.wcrl.CrawlerController;
import research.vn.wcrl.utils.ContextLoader;
import research.vn.wcrl.utils.FileUtils;
import research.vn.wcrl.utils.PropertyUtils;
import roadrunner.application.ExtractFromHtml;

/**
 * CrawlerTest
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CrawlerTest
{
    private static Logger logger = Logger.getLogger("CrawlerTest");
    private static IWrapperService wrapperService = (IWrapperService)ContextLoader.getInstance().getBean("wrapperService");
    private static ISourceService sourceService = (ISourceService)ContextLoader.getInstance().getBean("sourceService");
    
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args)
    {
        String contChar = "";
        boolean isContinue = true;
        do
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input site code:");
            String siteName = sc.nextLine();
            
            if (siteName == null || siteName.length() == 0)
            {
                siteName = "VNW";
                System.out.println("Sitename default:'" + siteName + "'");
            }
            
            System.out.println("Data extract:");
            System.out.println("0: Run crawler.");
            System.out.println("1: Generate wrapper");
            System.out.println("2: Extract data");
            System.out.println("Please input a number:");
            int value = sc.nextInt();

            ExtractFromHtml extractor = new ExtractFromHtml();
            extractor.setInputFileEncoding("utf8");
            String inputHtmlsFolder = String.format(PropertyUtils.getInstance()
                                                            .getValue("input.folder.html"),
                                                    siteName);
            String outputWrapperFolder = String.format(PropertyUtils.getInstance()
                                                               .getValue("output.folder.wrapper"),
                                                       siteName);
            String outputHtmlFolder = String.format(PropertyUtils.getInstance()
                                                       .getValue("output.folder.html"),
                                               siteName);
            
            String outputResultFolder = String.format(PropertyUtils.getInstance()
                                                              .getValue("output.folder.result"),
                                                      siteName);
    
            switch (value)
            {
                case 0:
                    FileUtils.getInstance().cleanUp(outputHtmlFolder);
                    CrawlerController.crawl();
                    break;
                case 1:
                    extractor.generateWrapper(inputHtmlsFolder, siteName);
                    File file = new File(String.format("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\%1$s\\%1$s00.xml", siteName));
                    FileInputStream fis;
                    byte[] fileContent = null;
                    try
                    {
                        fis = new FileInputStream(file);
                        fileContent = new byte[(int)file.length()];
                        fis.read(fileContent);
                        
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
                    catch (FileNotFoundException e1)
                    {
                        logger.error("Error occurred: " + e1.getMessage(), e1);
                    }
                    catch (IOException e)
                    {
                        logger.error("Error occurred: " + e.getMessage(), e);
                    }

                    break;
                case 2:
                    Wrapper wrapper = wrapperService.selectByName(siteName + "00.xml");
                    
                    try
                    {
                        FileOutputStream fos = new FileOutputStream(String.format("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\%1$s\\%1$s00.xml", siteName));
                        fos.write(wrapper.getContent().getBytes());
                        fos.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        logger.error("Error occurred: " + e.getMessage(), e);
                    }
                    catch (IOException e)
                    {
                        logger.error("Error occurred: " + e.getMessage(), e);
                    }
                    
                    extractor.extractAll("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\VNW\\VNW00.xml", outputHtmlFolder, outputResultFolder);
                    break;
                default:
            }
            System.out.println("press any to continue, or 'E' to exit");
            contChar = sc.next();
            isContinue = !"e".equalsIgnoreCase(contChar);
        } while (isContinue);
        
        System.out.println("The end.");
    }
}


/*
 * Changes:
 * $Log: $
 */