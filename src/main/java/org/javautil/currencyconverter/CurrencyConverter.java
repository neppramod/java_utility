package org.javautil.currencyconverter;

import org.javautil.webutil.WebUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Idea used from here:
// https://github.com/om06/google-currency
public class CurrencyConverter {

    // Note all three %s will be replaced with from, to, and amount
    private static final String URI = "https://www.google.com/search?q=convert+%s+%s+to+%s&hl=en&lr=lang_en";

    // Update the codes if new country gets added
    private static final Map<String, String> codes = new HashMap<>();
    static {
        codes.put("AFN", "Afghan Afghani");
        codes.put("ALL", "Albanian Lek");
        codes.put("DZD", "Algerian Dinar");
        codes.put("AOA", "Angolan Kwanza");
        codes.put("ARS", "Argentine Peso");
        codes.put("AMD", "Armenian Dram");
        codes.put("AWG", "Aruban Florin");
        codes.put("AUD", "Australian Dollar");
        codes.put("AZN", "Azerbaijani Manat");
        codes.put("BSD", "Bahamian Dollar");
        codes.put("BHD", "Bahraini Dinar");
        codes.put("BBD", "Bajan dollar");
        codes.put("BDT", "Bangladeshi Taka");
        codes.put("BYR", "Belarusian Ruble");
        codes.put("BYN", "Belarusian Ruble");
        codes.put("BZD", "Belize Dollar");
        codes.put("BMD", "Bermudan Dollar");
        codes.put("BTN", "Bhutan currency");
        codes.put("BTC", "Bitcoin");
        codes.put("BCH", "Bitcoin Cash");
        codes.put("BOB", "Bolivian Boliviano");
        codes.put("BAM", "Bosnia-Herzegovina Convertible Mark");
        codes.put("BWP", "Botswanan Pula");
        codes.put("BRL", "Brazilian Real");
        codes.put("BND", "Brunei Dollar");
        codes.put("BGN", "Bulgarian Lev");
        codes.put("BIF", "Burundian Franc");
        codes.put("XPF", "CFP Franc");
        codes.put("KHR", "Cambodian riel");
        codes.put("CAD", "Canadian Dollar");
        codes.put("CVE", "Cape Verdean Escudo");
        codes.put("KYD", "Cayman Islands Dollar");
        codes.put("XAF", "Central African CFA franc");
        codes.put("CLP", "Chilean Peso");
        codes.put("CLF", "Chilean Unit of Account (UF)");
        codes.put("CNY", "Chinese Yuan");
        codes.put("CNH", "Chinese Yuan (offshore)");
        codes.put("COP", "Colombian Peso");
        codes.put("KMF", "Comorian franc");
        codes.put("CDF", "Congolese Franc");
        codes.put("CRC", "Costa Rican Colón");
        codes.put("HRK", "Croatian Kuna");
        codes.put("CUP", "Cuban Peso");
        codes.put("CZK", "Czech Koruna");
        codes.put("DKK", "Danish Krone");
        codes.put("DJF", "Djiboutian Franc");
        codes.put("DOP", "Dominican Peso");
        codes.put("XCD", "East Caribbean Dollar");
        codes.put("EGP", "Egyptian Pound");
        codes.put("ETH", "Ether");
        codes.put("ETB", "Ethiopian Birr");
        codes.put("EUR", "Euro");
        codes.put("FJD", "Fijian Dollar");
        codes.put("GMD", "Gambian dalasi");
        codes.put("GEL", "Georgian Lari");
        codes.put("GHC", "Ghanaian Cedi");
        codes.put("GHS", "Ghanaian Cedi");
        codes.put("GIP", "Gibraltar Pound");
        codes.put("GTQ", "Guatemalan Quetzal");
        codes.put("GNF", "Guinean Franc");
        codes.put("GYD", "Guyanaese Dollar");
        codes.put("HTG", "Haitian Gourde");
        codes.put("HNL", "Honduran Lempira");
        codes.put("HKD", "Hong Kong Dollar");
        codes.put("HUF", "Hungarian Forint");
        codes.put("ISK", "Icelandic Króna");
        codes.put("INR", "Indian Rupee");
        codes.put("IDR", "Indonesian Rupiah");
        codes.put("IRR", "Iranian Rial");
        codes.put("IQD", "Iraqi Dinar");
        codes.put("ILS", "Israeli New Shekel");
        codes.put("JMD", "Jamaican Dollar");
        codes.put("JPY", "Japanese Yen");
        codes.put("JOD", "Jordanian Dinar");
        codes.put("KZT", "Kazakhstani Tenge");
        codes.put("KES", "Kenyan Shilling");
        codes.put("KWD", "Kuwaiti Dinar");
        codes.put("KGS", "Kyrgystani Som");
        codes.put("LAK", "Laotian Kip");
        codes.put("LBP", "Lebanese pound");
        codes.put("LSL", "Lesotho loti");
        codes.put("LRD", "Liberian Dollar");
        codes.put("LYD", "Libyan Dinar");
        codes.put("LTC", "Litecoin");
        codes.put("MOP", "Macanese Pataca");
        codes.put("MKD", "Macedonian Denar");
        codes.put("MGA", "Malagasy Ariary");
        codes.put("MWK", "Malawian Kwacha");
        codes.put("MYR", "Malaysian Ringgit");
        codes.put("MVR", "Maldivian Rufiyaa");
        codes.put("MRO", "Mauritanian Ouguiya (1973–2017)");
        codes.put("MUR", "Mauritian Rupee");
        codes.put("MXN", "Mexican Peso");
        codes.put("MDL", "Moldovan Leu");
        codes.put("MAD", "Moroccan Dirham");
        codes.put("MZM", "Mozambican metical");
        codes.put("MZN", "Mozambican metical");
        codes.put("MMK", "Myanmar Kyat");
        codes.put("TWD", "New Taiwan dollar");
        codes.put("NAD", "Namibian dollar");
        codes.put("NPR", "Nepalese Rupee");
        codes.put("ANG", "Netherlands Antillean Guilder");
        codes.put("NZD", "New Zealand Dollar");
        codes.put("NIO", "Nicaraguan Córdoba");
        codes.put("NGN", "Nigerian Naira");
        codes.put("NOK", "Norwegian Krone");
        codes.put("OMR", "Omani Rial");
        codes.put("PKR", "Pakistani Rupee");
        codes.put("PAB", "Panamanian Balboa");
        codes.put("PGK", "Papua New Guinean Kina");
        codes.put("PYG", "Paraguayan Guarani");
        codes.put("PHP", "Philippine Piso");
        codes.put("PLN", "Poland złoty");
        codes.put("GBP", "Pound sterling");
        codes.put("QAR", "Qatari Rial");
        codes.put("ROL", "Romanian Leu");
        codes.put("RON", "Romanian Leu");
        codes.put("RUR", "Russian Ruble");
        codes.put("RUB", "Russian Ruble");
        codes.put("RWF", "Rwandan franc");
        codes.put("SVC", "Salvadoran Colón");
        codes.put("SAR", "Saudi Riyal");
        codes.put("CSD", "Serbian Dinar");
        codes.put("RSD", "Serbian Dinar");
        codes.put("SCR", "Seychellois Rupee");
        codes.put("SLL", "Sierra Leonean Leone");
        codes.put("SGD", "Singapore Dollar");
        codes.put("PEN", "Sol");
        codes.put("SBD", "Solomon Islands Dollar");
        codes.put("SOS", "Somali Shilling");
        codes.put("ZAR", "South African Rand");
        codes.put("KRW", "South Korean won");
        codes.put("VEF", "Sovereign Bolivar");
        codes.put("XDR", "Special Drawing Rights");
        codes.put("LKR", "Sri Lankan Rupee");
        codes.put("SSP", "Sudanese pound");
        codes.put("SDG", "Sudanese pound");
        codes.put("SRD", "Surinamese Dollar");
        codes.put("SZL", "Swazi Lilangeni");
        codes.put("SEK", "Swedish Krona");
        codes.put("CHF", "Swiss Franc");
        codes.put("TJS", "Tajikistani Somoni");
        codes.put("TZS", "Tanzanian Shilling");
        codes.put("THB", "Thai Baht");
        codes.put("TOP", "Tongan Paʻanga");
        codes.put("TTD", "Trinidad & Tobago Dollar");
        codes.put("TND", "Tunisian Dinar");
        codes.put("TRY", "Turkish lira");
        codes.put("TMM", "Turkmenistan manat");
        codes.put("TMT", "Turkmenistan manat");
        codes.put("UGX", "Ugandan Shilling");
        codes.put("UAH", "Ukrainian hryvnia");
        codes.put("AED", "United Arab Emirates Dirham");
        codes.put("USD", "United States Dollar");
        codes.put("UYU", "Uruguayan Peso");
        codes.put("UZS", "Uzbekistani Som");
        codes.put("VND", "Vietnamese dong");
        codes.put("XOF", "West African CFA franc");
        codes.put("YER", "Yemeni Rial");
        codes.put("ZMW", "Zambian Kwacha");
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

    public static void main(String[] args) {
        String from = "NZD";
        String to = "PGK";
        String amount = "200";

        String res = convert(from, to, amount);
        System.out.println(res);
    }
}