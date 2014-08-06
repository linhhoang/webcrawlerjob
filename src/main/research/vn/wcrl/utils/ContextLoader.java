/*
 * ContextLoader.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The class utils for load ApplicationContext
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class ContextLoader
{
    private static ContextLoader instance;
    private ApplicationContext context = new ClassPathXmlApplicationContext("/webcrawler-context.xml");
    
    /**
     * get instance of ContextLoader
     *
     * @return ContextLoader
     */
    public static ContextLoader getInstance()
    {
        if (instance == null)
        {
            instance = new ContextLoader();
        }
        
        return instance;
    }
    
    
    /**
     * get bean from generic class
     *
     * @param <T>
     * @param clazz
     * @return T
     */
    public <T> T getBean(Class<T> clazz)
    {
        return context.getBean(clazz);
    }
    
    /**
     * get bean from class name
     *
     * @param className
     * @return bean object
     */
    public Object getBean(String className)
    {
        return context.getBean(className);
    }
}


/*
 * Changes:
 * $Log: $
 */