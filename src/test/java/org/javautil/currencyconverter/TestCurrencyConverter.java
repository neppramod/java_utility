package org.javautil.currencyconverter;

import org.javautil.webutil.WebUtility;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;


public class TestCurrencyConverter {
    @Test
    public void testConvert() {
        try {
            MockedStatic<WebUtility> mockedStatic = mockStatic(WebUtility.class);
            mockedStatic.when(() -> WebUtility.readWebPage(anyString())).
                    thenReturn("Some text 100 INR some text 1.21 United States Dollar some text");

            String convertedString = CurrencyConverter.convert("INR", "USD", "100");
            assertEquals(convertedString, "1.21");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
