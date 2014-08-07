package research.vn.wcrl;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import research.vn.careerservice.service.IReferenceService;
import research.vn.careerservice.service.ISourceService;
import research.vn.careerservice.utils.ConfigurationUtils;
import research.vn.careerservice.vo.Reference;
import research.vn.careerservice.vo.Source;
import research.vn.wcrl.sites.CrawlerSite;
import research.vn.wcrl.sites.CrawlerSiteFactory;
import research.vn.wcrl.utils.ContextLoader;
import research.vn.wcrl.utils.FileUtils;
import research.vn.wcrl.utils.PropertyUtils;
import research.vn.wcrl.utils.RandomKeyUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


/*
 * CustomeWebCrawler.java
 *
 */

/**
 * The custom class for Web crawler
 * 
 * @author hcnlinh
 * @version $Revision: $
 */
public class CustomWebCrawler extends WebCrawler
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(CustomWebCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
                                                           + "|png|tiff?|mid|mp2|mp3|mp4"
                                                           + "|wav|avi|mov|mpeg|ram|m4v|pdf"
                                                           + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
    private IReferenceService referenceService = (IReferenceService)ContextLoader.getInstance().getBean("referenceService");
    private ISourceService sourceService = (ISourceService)ContextLoader.getInstance().getBean("sourceService");

    /**
     * @see edu.uci.ics.crawler4j.crawler.WebCrawler#shouldVisit(edu.uci.ics.crawler4j.url.WebURL)
     */
    @Override
    public boolean shouldVisit(WebURL url)
    {
        
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith(PropertyUtils.getInstance().getValue("fetchSite"));
    }


    /**
     * @see edu.uci.ics.crawler4j.crawler.WebCrawler#visit(edu.uci.ics.crawler4j.crawler.Page)
     */
    @Override
    public void visit(Page page)
    {
        String url = page.getWebURL().getURL();
        log.info("visited URL: " + url);
        
        if (isExistUrl(url))
        {
            logger.info("'" + url + "' is exist already.");
            return;
        }
        
        if (page.getParseData() instanceof HtmlParseData)
        {
            HtmlParseData htmlParseData = (HtmlParseData)page.getParseData();

            String text = htmlParseData.getText();
            
            String html = htmlParseData.getHtml();
            
            List<WebURL> links = htmlParseData.getOutgoingUrls();
            
            Document document = Jsoup.parse(html);
            
            String siteCode = PropertyUtils.getInstance().getValue("fetchSiteCode");
            CrawlerSite crawlerSite = CrawlerSiteFactory.createCrawlerSite();
            
            if (crawlerSite != null)
            {
                String content = crawlerSite.preprocessing(document, siteCode);
                
                if (content != null)
                {
                    Whitelist whiteList = new Whitelist();
                    whiteList.addTags(new String[] {"blockquote", "caption",
                                                    "cite", "code", "col", "colgroup", "dd", "div",
                                                    "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5",
                                                    "h6", "img", "li", "ol", "pre", "q",
                                                    "small", "strike", "strong", "sub", "sup",
                                                    "table", "tbody", "td", "tfoot", "th", "thead",
                                                    "tr", "u", "ul"})
//                            .addAttributes("a", new String[] {"href", "title"})
                            .addAttributes("blockquote", new String[] {"cite"})
                            .addAttributes("col", new String[] {"span", "width"})
                            .addAttributes("colgroup", new String[] {"span", "width"})
                            .addAttributes("img",
                                           new String[] {"align", "alt", "height", "src", "title",
                                                         "width"})
                            .addAttributes("ol", new String[] {"start", "type"})
                            .addAttributes("q", new String[] {"cite"})
                            .addAttributes("table", new String[] {"summary", "width"})
                            .addAttributes("td",
                                           new String[] {"abbr", "axis", "colspan", "rowspan",
                                                         "width"})
                            .addAttributes("th",
                                           new String[] {"abbr", "axis", "colspan", "rowspan",
                                                         "scope", "width"})
                            .addAttributes("ul", new String[] {"type"})
                            .addProtocols("a",
                                          "href",
                                          new String[] {"ftp", "http", "https", "mailto"})
                            .addProtocols("blockquote", "cite", new String[] {"http", "https"})
                            .addProtocols("img", "src", new String[] {"http", "https"})
                            .addProtocols("q", "cite", new String[] {"http", "https"});
                    whiteList.addTags("div")
                            .addAttributes("div", "id", "class")
                            .addAttributes("h1", "id", "class", "itemprop");

                    content = Jsoup.clean(html, whiteList);
                    String randomKey = RandomKeyUtils.getInstance().random(8);
                    String filePath = getFilePath(randomKey, siteCode, url);
                    
                    // write text file
                    FileUtils.getInstance().writeTextFile(content, filePath, "html");
                    
                    writeToReference(url, siteCode, randomKey);
                }
            }
            
            log.debug("Text length: " + text.length());
            log.debug("Html length: " + html.length());
            log.debug("Number of outgoing links: " + links.size());
        }

    }
    

    /**
     * write url to reference
     *
     * @param url
     * @param siteCode 
     * @param randomKey 
     */
    private void writeToReference(String url, String siteCode, String randomKey)
    {
        try
        {
            Source source = sourceService.selectById(siteCode);
            
            if (source == null)
            {
                logger.error("Cannot read Source of '" + siteCode + "'");
                return;
            }
            Reference reference = new Reference();
            reference.setUrl(url);
            reference.setId(randomKey);
            reference.setCreator(0L);
            reference.setCreateDate(new Date());
            reference.setExtractedFlg(false);
            reference.setSourceKey(source.getKey());
            reference.setDeleteFlg(false);
            referenceService.insert(reference);
        } catch (PersistenceException e)
        {
            logger.error(e.getMessage());
        }
        
    }


    /**
     * Method description
     *
     * @param url
     * @return isExist
     */
    private boolean isExistUrl(String url)
    {
        try
        {
            Reference reference = referenceService.selectByUrl(url);
            return reference != null;
        } catch (PersistenceException e)
        {
            logger.error(e.getMessage());
        }
        
        return false;
    }


    /**
     * get filePath
     *
     * @param siteCode 
     * @param url
     * @param randomKey
     * @return String
     */
    public String getFilePath(String randomKey, String siteCode, String url)
    {
        String fileName = "unname";
        
        if (url != null && !url.isEmpty())
        {
            fileName = url.replaceAll("\\W", "");
            
            if (fileName.length() > 20)
            {
                fileName = fileName.substring(fileName.length() - 20);
            }
        }
        
        String htmlOutputFolderConfig = PropertyUtils.getInstance().getValue("output.folder.html");
        if (StringUtils.isEmpty(htmlOutputFolderConfig))
        {
            log.error("Output html folder is null or empty.");
            return null;
        }
        
        String htmlOutputFolderPath = String.format(htmlOutputFolderConfig, siteCode);
        FileUtils.getInstance().createFolder(htmlOutputFolderPath);
        
        return htmlOutputFolderPath + File.separator + siteCode + "_" + randomKey + "_" + fileName;
    }
    


}

/*
 * Changes: $Log: $
 */