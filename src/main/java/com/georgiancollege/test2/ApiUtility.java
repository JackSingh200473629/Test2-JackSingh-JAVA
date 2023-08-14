package com.georgiancollege.test2;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiUtility {
    public static ApiResponse getDataFromApi(){
        String url = "https://lamp.computerstudi.es/~PriyanshPriyansh01/java/test2/users.json";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try{
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse
                    .BodyHandlers
                    .ofString());
            Gson gson = new Gson();
            return gson.fromJson(response.body(), ApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
