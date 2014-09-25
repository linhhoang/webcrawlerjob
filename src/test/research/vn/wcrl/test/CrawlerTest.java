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
                siteName = "VNWs";
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
            FileUtils.getInstance().initFolder("output.root");
			String inputHtmlsFolder = FileUtils.getInstance().initFoler("input.folder.html", siteName);
			String outputWrapperFolder = FileUtils.getInstance().initFoler("output.folder.wrapper", siteName);
            String outputHtmlFolder = FileUtils.getInstance().initFoler("output.folder.html", siteName);
            String outputResultFolder = FileUtils.getInstance().initFoler("output.folder.result", siteName);
            
            String wrapperFilePath = String.format(outputWrapperFolder + File.separator + "%1$s00.xml", siteName);
            switch (value)
            {
                case 0:
                    FileUtils.getInstance().cleanUp(outputHtmlFolder);
                    CrawlerController.crawl();
                    break;
                case 1:
                    extractor.generateWrapper(inputHtmlsFolder, siteName);
                    File file = new File(wrapperFilePath);
                    writeWrapper(siteName, file);

                    break;
                case 2:
//                    overwriteWrapper(siteName, wrapperFilePath);
                    File resultFolder = new File(outputResultFolder);
                    if (!resultFolder.exists())
                    {
                        resultFolder.mkdir();
                    }
                    extractor.extractAll(wrapperFilePath, outputHtmlFolder, outputResultFolder);
                    break;
                default:
            }
            System.out.println("press any to continue, or 'E' to exit");
            contChar = sc.next();
            isContinue = !"e".equalsIgnoreCase(contChar);
        } while (isContinue);
        
        System.out.println("The end.");
    }
	
	
	private static void writeWrapper(String siteName, File file) {
		if (!file.exists())
		{
			System.out.println("Wrapper file does not exist. '" + file.getAbsolutePath() + "'");
			return;
		}
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
		    
		    Wrapper wrapper = wrapperService.selectByName(siteName + "00.xml");
		    
		    if (wrapper == null)
		    {
		    	Wrapper wrapperInsert = new Wrapper();
		    	wrapperInsert.setCreator(0L);
		    	wrapperInsert.setSourceKey(source.getKey());
		    	wrapperInsert.setCreateDate(new Date());
		    	wrapperInsert.setWrapperName(siteName + "00.xml");
		    	wrapperInsert.setDeleteFlg(false);
		    	wrapperInsert.setContent(new String(fileContent));
		    	wrapperService.insert(wrapperInsert);
		    }
		    else
		    {
		    	wrapper.setUpdater(0L);
		    	wrapper.setUpdateDate(new Date());
		    	wrapper.setDeleteFlg(false);
		    	wrapper.setContent(new String(fileContent));
		    	wrapperService.update(wrapper);
		    }
		    
		}
		catch (FileNotFoundException e1)
		{
		    logger.error("Error occurred: " + e1.getMessage(), e1);
		}
		catch (IOException e)
		{
		    logger.error("Error occurred: " + e.getMessage(), e);
		}
	}

	private static void overwriteWrapper(String siteName, String wrapperPath)
    {
		logger.info("Write content of '" + wrapperPath + "' to DB");
        Wrapper wrapper = wrapperService.selectByName(siteName + "00.xml");
        
        if (wrapper != null)
        {
        	try
        	{
        		FileOutputStream fos = new FileOutputStream(wrapperPath);
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
        }
        else
        {
        	File wrapperFile = new File(wrapperPath);
        	writeWrapper(siteName, wrapperFile);
        }
    }
}


/*
 * Changes:
 * $Log: $
 */