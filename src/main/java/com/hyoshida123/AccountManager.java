package com.hyoshida123;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManager {
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final ObjectMapper mapper = new ObjectMapper();
    private String url = "https://***API Domain***/users/";
    private Map<String, Integer> bankInfo = null;

    public AccountManager(String userId) {
        this.url = this.url + userId + "/accounts";
    }

    public void fetchBankInfo() throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            return;
        }

        bankInfo = new HashMap<>();
        List<Attributes> jsonList = mapper.readValue(response.body(), new TypeReference<List<Attributes>>() {});
        for (Attributes attributes: jsonList) {
            String bankName = attributes.getJson().get("name").asText();
            int balance = Integer.parseInt(attributes.getJson().get("balance").asText());
            this.bankInfo.put(bankName, balance);
        }
    }

    public Map<String, Integer> getBankInfo() {
        return this.bankInfo;
    }
}
