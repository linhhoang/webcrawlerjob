package research.vn.wcrl.test;

import java.util.Scanner;

import research.vn.wcrl.CrawlerController;
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

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String contChar = "";
        do
        {
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
                    break;
                case 2:
                    extractor.extractAll("D:\\workspaces\\ofwi\\webcrawlerJob\\output\\VNW\\VNW00.xml", outputHtmlFolder, outputResultFolder);
                    break;
                default:
            }
            System.out.println("press any to continue, or 'E' to exit");
            contChar = sc.next();
        } while (!"e".equalsIgnoreCase(contChar));
        
    }
}


/*
 * Changes:
 * $Log: $
 */