/*
 * Example.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.utils;

import java.io.CharArrayWriter;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author hcnlinh
 * @version $Revision: $
 */
public class Example extends DefaultHandler
{
    private CharArrayWriter field = new CharArrayWriter();
    private CharArrayWriter contents = new CharArrayWriter();
    
    /**
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException
    {
        System.out.println("SAX Event: START DOCUMENT");
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("SAX Event: END DOCUMENT");
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
        throws SAXException
    {
        field.reset();
        contents.reset();
        System.out.println("SAX Event: START ELEMENT[ " + localName + " ]");
        
        if ("attribute".equalsIgnoreCase(localName))
        {
            try
            {
                // Also, let's print the attributes if
                // there are any...
                for (int i = 0; i < attr.getLength(); i++)
                {
                    if ("label".equalsIgnoreCase(attr.getLocalName(i)))
                    {
                        if ("JOB_TITLE".equalsIgnoreCase(attr.getValue(i)))
                        {
                            field.write(attr.getValue(i));
                        }
                        else if ("PROFESSION".equalsIgnoreCase(attr.getValue(i)))
                        {
                            field.write(attr.getValue(i));
                        }
                        else if ("WORKING_TIME".equalsIgnoreCase(attr.getValue(i)))
                        {
                            field.write(attr.getValue(i));
                        }
                        else if ("SALARY".equalsIgnoreCase(attr.getValue(i)))
                        {
                            field.write(attr.getValue(i));
                        }
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException
    {
        if ("JOB_TITLE".equalsIgnoreCase(field.toString()))
        {
            System.out.println("Job title: " + contents.toString());
        }
        else if ("PROFESSION".equalsIgnoreCase(field.toString()))
        {
            System.out.println("Profession " + contents.toString());
        }
        else if ("WORKING_TIME".equalsIgnoreCase(field.toString()))
        {
            System.out.println("Working time: " + contents.toString());
        }
        else if ("SALARY".equalsIgnoreCase(field.toString()))
        {
            System.out.println("Salary: " + contents.toString());
        }
//        System.out.println("SAX Event: END ELEMENT[ " + localName + " ]");
    }


    /**
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        contents.write(ch, start, length);
//        System.out.print("SAX Event: CHARACTERS[ ");
//        try
//        {
//            OutputStreamWriter outw = new OutputStreamWriter(System.out);
//            outw.write(ch, start, length);
//            outw.flush();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        System.out.println(" ]");
    }

}

/*
 * Changes: $Log: $
 */