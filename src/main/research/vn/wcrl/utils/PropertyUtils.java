/*
 * PropertyUtils.java
 *
 */
package research.vn.wcrl.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * The utility class to get properties from resource
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public final class PropertyUtils
{

    /** The Constant LOG. */
    private static final Logger log = Logger.getLogger(PropertyUtils.class);
    
    private static PropertyUtils instance = null;
    private Properties mappingProperties = null;
    
    
    /**
     * get instance
     *
     * @return PropertyUtils instance
     */
    public static PropertyUtils getInstance()
    {
        if (instance == null)
        {
            instance = new PropertyUtils();
        }
        
        return instance;
    }
    
    /**
     * Constructor
     */
    private PropertyUtils()
    {
        setProperties(Constants.CRAWLER_CONFIG_FILE);
    }
    
    /**
     * set Properties
     *
     * @param resource
     */
    public void setProperties(String resource)
    {
        try
        {
            mappingProperties = PropertiesLoaderUtils.loadAllProperties(resource);
        }
        catch (IOException e)
        {
            log.error("Error occurred: " + e.getMessage(), e);
        }
    }
    
    /**
     * get value
     *
     * @param configKey
     * @return String
     */
    public String getValue(String configKey)
    {
        return mappingProperties.getProperty(configKey);
    }

    /**
     * get integer value of config key
     *
     * @param configKey
     * @return IntValue
     */
    public Integer getIntValue(String configKey)
    {
        String value = mappingProperties.getProperty(configKey).trim();
        
        if (StringUtils.isEmpty(value))
        {
            return null; 
        }
        Integer intValue = null;
        
        try
        {
            intValue = Integer.valueOf(value);
        }
        catch (NumberFormatException e)
        {
            log.error("Error occurred: " + e.getMessage(), e);
        }
        
        return intValue;
    }
}


/*
 * Changes:
 * $Log: $
 */