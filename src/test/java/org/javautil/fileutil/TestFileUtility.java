package org.javautil.fileutil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFileUtility {

    @Test
    // This is more of a user test than unit test
    public void testReadFile() {
        try {
            String fileText = FileUtility.readFile("config/currency_codes.txt");
            String[] allLines = fileText.split(System.lineSeparator());
            assertEquals(allLines[allLines.length-1], "ZMW Zambian Kwacha");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
