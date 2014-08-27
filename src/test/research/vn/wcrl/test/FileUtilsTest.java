/*
 * PropertiesTest.java
 *
 * Copyright by Orell Füssli Wirtschaftsinformationen AG
 * Zürich
 * All rights reserved.
 */
package research.vn.wcrl.test;

import org.testng.annotations.Test;

import research.vn.wcrl.utils.FileUtils;

/**
 * 
 *
 * @author hcnlinh
 * @version $Revision:  $
 */
public class FileUtilsTest
{

    @Test
    public void getValue()
    {
        String siteName = "VNWs";
        FileUtils.getInstance().initFolder("output.root");
        FileUtils.getInstance().initFoler("input.folder.html", siteName );
        FileUtils.getInstance().initFoler("output.folder.wrapper", siteName);
        FileUtils.getInstance().initFoler("output.folder.html", siteName);
        FileUtils.getInstance().initFoler("output.folder.result", siteName);
    }
}


/*
 * Changes:
 * $Log: $
 */