/*
 * RandomKey.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.utils;

import java.security.SecureRandom;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class RandomKeyUtils
{

    private static RandomKeyUtils instance;
    
    /**
     * get instance
     *
     * @return RandomKeyUtils
     */
    public static RandomKeyUtils getInstance()
    {
        if (instance == null)
        {
            instance = new RandomKeyUtils();
        }
        
        return instance;
    }
    
    
    /**
     * get random key
     *
     * @return Key
     */
    public String random()
    {
        return random(8);
    }

    
    /**
     * get random key
     * @param seeds 
     *
     * @return Key
     */
    public String random(byte[] seeds)
    {
        return random(seeds, 8);
    }

    
    /**
     * get random key
     * @param byteNum 
     *
     * @return Key
     */
    public String random(int byteNum)
    {
        SecureRandom srnd = new SecureRandom();
        byte[] aesKey = new byte[byteNum]; // 16 bytes = 128 bits
        srnd.nextBytes(aesKey);
        
        return Utils.toHex(aesKey);
    }
    

    /**
     * get random key
     * @param seeds 
     * @param byteNum 
     *
     * @return Key
     */
    public String random(byte[] seeds, int byteNum)
    {
        SecureRandom srnd = new SecureRandom(seeds);
        byte[] aesKey = new byte[byteNum]; // 16 bytes = 128 bits
        srnd.nextBytes(aesKey);
        
        return Utils.toHex(aesKey);
    }
}

class Utils
{
    private static String digits = "0123456789abcdef";
    
    /**
     * Return length many bytes of the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @param length the number of bytes in the data block to be converted.
     * @return a hex representation of length bytes of data.
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer  buf = new StringBuffer();
        
        for (int i = 0; i != length; i++)
        {
            int v = data[i] & 0xff;
            
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        
        return buf.toString();
    }
    
    /**
     * Return the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }
}


/*
 * Changes:
 * $Log: $
 */