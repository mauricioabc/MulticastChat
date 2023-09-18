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
import java.nio.charset.StandardCharsets;
import java.util.Map;
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
    
    public String getServerPublicKey() throws IOException, InterruptedException{
        String requestBody = "";
        // Crie um mapa de cabeçalhos para a solicitação POST (opcional)
        Map<String, String> headers = Map.of(
                "Content-Type", "application/json"
        );

        // Crie um cliente HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Construa a solicitação POST
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(apiBaseLink + "/connection"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8));

        // Adicione os cabeçalhos à solicitação POST
        headers.forEach(requestBuilder::header);

        // Construa a solicitação final
        HttpRequest request = requestBuilder.build();

        // Envie a solicitação POST e obtenha a resposta
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Processar a resposta
            int statusCode = response.statusCode();
            String responseBody = response.body();
            
            // Analisar o JSON
            JSONObject jsonObject = new JSONObject(responseBody);
            String serverPublicKey = jsonObject.getString("server_public_key");
            
            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);
            return serverPublicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean getChatData(String chatName) throws IOException, InterruptedException{
        String serverPublicKey = getServerPublicKey();
        
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
