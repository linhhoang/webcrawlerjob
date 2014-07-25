/*
 * CrawlerTest.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import research.vn.wcrl.utils.FileUtils;
import research.vn.wcrl.utils.PropertyUtils;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * The controller class for Web crawler
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CrawlerController
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(CrawlerController.class);
    
    /** D_TEMP_CRAWLER */
    private static final int POLITENESS_DELAY = 200;
    private static final int MAX_DEPTH_OF_CRAWLING = 3;

    /**
     * crawl method
     *
     */
    public static void crawl()
    {
        log.info("Start the web crawler application.");
        
        CrawlConfig crawlConfig = new CrawlConfig();
        crawlConfig.setPolitenessDelay(POLITENESS_DELAY);
        crawlConfig.setMaxDepthOfCrawling(MAX_DEPTH_OF_CRAWLING);
        crawlConfig.setMaxPagesToFetch(Integer.valueOf(PropertyUtils.getInstance().getValue("maxPagesToFetch")));
        String crawlerStorageFolder = PropertyUtils.getInstance().getValue("output.crawler.store");
        Integer numberOfCrawlers = PropertyUtils.getInstance().getIntValue("crawler.number");
        
        crawlConfig.setCrawlStorageFolder(crawlerStorageFolder);
        
        FileUtils.getInstance().cleanUp(crawlerStorageFolder);
        
        PageFetcher pageFetcher = new PageFetcher(crawlConfig);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try
        {
            CrawlController crawlController = new CrawlController(crawlConfig,
                                                                  pageFetcher, robotstxtServer);
            
            crawlController.addSeed(PropertyUtils.getInstance().getValue("fetchSiteSeed.1"));
            crawlController.start(CustomWebCrawler.class, numberOfCrawlers);
            log.info("The crawler thead started.");
        }
        catch (Exception e)
        {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

}


/*
 * Changes:
 * $Log: $
 */