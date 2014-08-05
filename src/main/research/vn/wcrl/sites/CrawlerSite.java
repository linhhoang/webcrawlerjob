/*
 * CrawlerSite.java
 *
 */
package research.vn.wcrl.sites;

import org.jsoup.nodes.Document;

import research.vn.wcrl.bo.JobInfo;

/**
 * interface class of CrawlerSite
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public interface CrawlerSite
{

    /**
     * parse document
     * 
     * @param document
     * @param siteCode 
     * @param filePath 
     * @return JobInfo
     */
    public abstract JobInfo parse(Document document, String siteCode, String filePath);
    
    
    /**
     * pre-processing html document
     *
     * @param document
     * @param siteCode
     * @return Document
     */
    public abstract String preprocessing(Document document, String siteCode);
}


/*
 * Changes:
 * $Log: $
 */