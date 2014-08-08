/*
 * XMLLoader.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.utils;

import java.io.FileReader;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * @author hcnlinh
 * @version $Revision: $
 */
public class XMLLoader
{

    /**
     * Method description
     *
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Example1 SAX Events:");
        try
        {
            // Create SAX 2 parser...
            XMLReader xr = XMLReaderFactory.createXMLReader();
            // Set the ContentHandler...
            xr.setContentHandler(new Example());
            // Parse the file...
            xr.parse(new InputSource(new FileReader("output/vieclamdanang/result/res_sample1.html-to-.xml")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

/*
 * Changes: $Log: $
 */