package com.example.tp_java_s9_tripodi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {
    public static String fetchFlightData(String accessKey, String arrivalIATA) {
        try {
            // accessKey = "73c01d9141ea4c4594a8525812d1a79a"; Test de la clée directement dans le programme.
            String url = "http://api.aviationstack.com/v1/flights?access_key=" + accessKey + "&arr_iata=" + arrivalIATA;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); // Retourner la réponse JSON sous forme de chaîne
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
