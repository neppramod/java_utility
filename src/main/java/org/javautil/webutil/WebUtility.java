package org.javautil.webutil;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebUtility {
    private static HttpClient client = HttpClient.newHttpClient();
    private static final String USER_AGENT_VALUE = "Mozilla/5.0 Firefox/26.0";
    public static String readWebPage(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .setHeader("User-Agent", USER_AGENT_VALUE)
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
