package com.hyoshida123;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UsernameManager {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final ObjectMapper mapper = new ObjectMapper();
    private String url = "https://***API Domain***/users/";
    private String username = null;

    public UsernameManager(String userId) {
        this.url += userId;
    }

    public void fetchUsername() throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            this.username = "そのユーザーは存在しません。";
            return;
        }

        Attributes attributes = mapper.readValue(response.body(), Attributes.class);
        this.username = attributes.getJson().get("name").asText();
    }

    public String getUsername() {
        return this.username;
    }

}
