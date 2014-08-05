/*
 * CrawlerSiteVNW.java
 *
 */
package research.vn.wcrl.sites;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import research.vn.wcrl.bo.JobInfo;
import research.vn.wcrl.utils.HtmlParseUtils;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class CrawlerSiteImpl implements CrawlerSite
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(CrawlerSiteImpl.class);
    
    /**
     * @see research.vn.wcrl.sites.CrawlerSite#parse(org.jsoup.nodes.Document, String, String)
     */
    public JobInfo parse(Document document, String siteCode, String filePath)
    {
        
        return null;
    }

    /**
     * @see research.vn.wcrl.sites.CrawlerSite#preprocessing(org.jsoup.nodes.Document, java.lang.String)
     */
    public String preprocessing(Document document, String siteCode)
    {
        try
        {
            String contentBlock = HtmlParseUtils.parse(siteCode, document);
            
            return contentBlock;
        }
        catch (InstantiationException e)
        {
            log.warn("Error occurred: " + e.getMessage(), e);
        }
        catch (IllegalAccessException e)
        {
            log.warn("Error occurred: " + e.getMessage(), e);
        }
        catch (InvocationTargetException e)
        {
            log.warn("Error occurred: " + e.getMessage(), e);
        }
        return null;
    }

}


/*
 * Changes:
 * $Log: $
 */