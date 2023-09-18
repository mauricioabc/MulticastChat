package com.chat.Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class IntegrationService {

    private String apiBaseLink;
    
    public IntegrationService(String apiBaseLink) {
        this.apiBaseLink = apiBaseLink;
    }
    
    public boolean apiStatus() throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiBaseLink))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        System.out.println("Código de resposta: " + statusCode);

        if (statusCode == 200) {
            String responseBody = response.body();
            System.out.println("Resposta da API: " + responseBody);

            // Analisar o JSON
            JSONObject jsonObject = new JSONObject(responseBody);
            String serverStatus = jsonObject.getString("server_status");

            // Verificar o status e retornar true ou false
            boolean isOnline = "online".equals(serverStatus);
            System.out.println("Status do servidor: " + serverStatus);
            return isOnline;
        } else {
            System.out.println("Erro na requisição GET");
        }
        return false;
    }
    
}
