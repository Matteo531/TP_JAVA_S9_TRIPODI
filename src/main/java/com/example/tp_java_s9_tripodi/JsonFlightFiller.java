package com.example.tp_java_s9_tripodi;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonFlightFiller {
    private ArrayList<Flight> list = new ArrayList<>();

    // Constructeur
    public JsonFlightFiller(String jsonString, World w) {
        try {
            // Convertir la chaîne JSON en InputStream
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();

            // Extraire le tableau "data" de l'objet JSON
            JsonArray results = obj.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    // Extraire les informations pertinentes pour un vol
                    String flightNumber = result.getString("flight_number", "Unknown");
                    JsonObject departure = result.getJsonObject("departure");
                    JsonObject arrival = result.getJsonObject("arrival");

                    String departureIATA = departure.getString("iata", "Unknown");
                    String arrivalIATA = arrival.getString("iata", "Unknown");

                    // Créer un objet Flight et l'ajouter à la liste
                    Flight flight = new Flight(flightNumber, departureIATA, arrivalIATA);
                    list.add(flight);

                } catch (Exception e) {
                    e.printStackTrace(); // Gestion des erreurs pour les entrées individuelles
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs pour le JSON global
        }
    }

    // Getter pour récupérer la liste des vols
    public ArrayList<Flight> getList() {
        return list;
    }
}
