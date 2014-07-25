/*
 * CrawlerSiteFactory.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.sites;

/**
 * The class factory for creating CrawlerSite
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CrawlerSiteFactory
{

    /**
     * create CrawlerSite
     *
     * @return CrawlerSite
     */
    public static CrawlerSite createCrawlerSite()
    {
        CrawlerSite crawlerSite = null;
        crawlerSite = new CrawlerSiteImpl();
        
        return crawlerSite;
    }
}


/*
 * Changes:
 * $Log: $
 */