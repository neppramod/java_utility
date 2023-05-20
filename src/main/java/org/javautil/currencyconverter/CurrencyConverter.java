package org.javautil.currencyconverter;

import org.javautil.fileutil.FileUtility;
import org.javautil.webutil.WebUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Idea used from here:
// https://github.com/om06/google-currency
public class CurrencyConverter {

    // Note all three %s will be replaced with from, to, and amount
    private static final String URI = "https://www.google.com/search?q=convert+%s+%s+to+%s&hl=en&lr=lang_en";

    // Update the codes if new country gets added
    private static final Map<String, String> codes = new HashMap<>();

    // Read Currency Code and Currency Name into codes map from config/currency_codes.txt
    static {
        String fileText = FileUtility.readFile("config/currency_codes.txt");
        String[] allLines = fileText.split(System.lineSeparator());

        for (String line : allLines) {
            String[] curLineText = line.split(" ");
            String key = curLineText[0];
            String value = Arrays.stream(curLineText).skip(1).collect(Collectors.joining(" "));
            codes.put(key, value);
        }
    }

    // Convert amount from - to
    public static String convert(String from, String to, String amount) {
        try {
            String uri = String.format(URI, amount, from, to);
            String webResponse = WebUtility.readWebPage(uri);
            return parseResponse(webResponse, from, to, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    // Extract just the converted currency value from result
    // Here we search from a number that has code's value after the number
    private static String parseResponse(String result, String from, String to, String amount) {
        String format = String.format("[\\d*\\,]*\\.\\d* %s", codes.get(to));
        Pattern pattern = Pattern.compile(format);
        Matcher m = pattern.matcher(result);

        if (m.find()) {
           String res = m.group();
           String formattedRes = res.replaceAll(codes.get(to), "");
           return formattedRes.trim();
        } else {
            return null;
        }
    }
}