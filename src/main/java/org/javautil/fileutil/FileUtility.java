package org.javautil.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUtility {

    /**
     * Read file text as it is including newline separator
     * The code that uses this code needs to handle the newline themselves
     *
     * @param fileName  Filename with relative or absolute path
     * @return Text contained in fileName
     */
    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }
}
