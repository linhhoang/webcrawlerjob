/*
 * LoopTest.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.test;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.KeyGenerator;

import org.apache.log4j.Logger;

import research.vn.wcrl.CrawlerController;
import research.vn.wcrl.utils.FileUtils;
import research.vn.wcrl.utils.PropertyUtils;
import research.vn.wcrl.utils.RandomKeyUtils;
import roadrunner.application.ExtractFromHtml;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class LoopTest
{
    public static void main(String[] args)
    {
        String contChar = "";
        boolean isContinue = true;
        do
        {
            System.out.println(RandomKeyUtils.getInstance().random(1));
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

            switch (value)
            {
                case 0:
                    System.out.println("Process 0");
                    break;
                case 1:
                    System.out.println("Process 1");
                    break;
                case 2:
                    System.out.println("Process 2");
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