package com.example.tp_java_s9_tripodi;
import java.io.BufferedReader;
import java.io.FileReader;


public class TestJson {
    public static void main(String[] args) {
        try {
            // Charger les données du monde
            World w = new World("data/airport-codes_no_comma.csv");

            // Lire le fichier test.txt contenant les données JSON
            BufferedReader br = new BufferedReader(new FileReader("data/test.txt"));
            String testJson = br.readLine();

            // Analyser les données avec JsonFlightFiller
            JsonFlightFiller jsonFlightFiller = new JsonFlightFiller(testJson, w);

            // Afficher les vols
            for (Flight flight : jsonFlightFiller.getFlights()) {
                System.out.println(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
