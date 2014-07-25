/*
 * HtmlParseUtils.java
 *
 */
package research.vn.wcrl.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * The utility class to parse HTML document to Java object via properties configuration file
 *
 * @author hli
 * @version $Revision: 1.9 $
 */
public final class HtmlParseUtils
{
    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(HtmlParseUtils.class);
    
    /**
     * Constructor
     */
    private HtmlParseUtils()
    {
        // nop
    }
    
    
    /**
     * parse Html to get content part with properties query.
     * 
     * @param site
     * @param document
     * @return html content
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String parse(String site, Document document)
        throws InstantiationException, IllegalAccessException, InvocationTargetException
    {
        log.debug("call parseHtmlToObjectWithPropertiesQuery.");
        String siteCd = Constants.STAR_SIGN;
        
        if (site != null)
        {
            siteCd = site;
        }
        Element contentBlockElement = getContentBlock(document, siteCd);
        if (contentBlockElement != null)
        {
            return document.html();
        }
        
        return null;
    }
    
    
    /**
     * is content block
     *
     * @param document
     * @param siteCd 
     * @return isParseObject
     */
    private static Element getContentBlock(Document document, String siteCd)
    {
        String isParseObjectCss = PropertyUtils.getInstance().getValue(siteCd + Constants.DOT + "contentBlock" + Constants.DOT + Constants.HTML_QUERY);
        Elements elements = document.select(isParseObjectCss);
        if (elements != null && elements.size() > 0)
        {
            return elements.get(0);
        }
        return null;
    }
}
