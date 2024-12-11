package com.example.tp_java_s9_tripodi;

import java.time.LocalDateTime;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private String airLineCode;// Code de la compagnie aérienne
    private String airlineName;// Nom complet de la compagnie aérienne
    private LocalDateTime arrivalTime;// Heure d'arrivée prévue
    private LocalDateTime departureTime;// Heure de départ prévue
    private int number;// Numéro du vol
    private String departureIATA; // Code IATA de l'aéroport de départ

    public Flight(String airLineCode, String airlineName, LocalDateTime departureTime, LocalDateTime arrivalTime, int number, String departureIATA) {
        this.airLineCode = airLineCode;
        this.airlineName = airlineName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.number = number;
        this.departureIATA = departureIATA;
    }

    // Code permattant de mettre les dates et heures en ISO 8601.
    public static void main(String[] args) {
        String dateStr = "2024-12-11T04:08:00+00:00";  // Exemple de date avec décalage horaire

        // Utiliser ZonedDateTime pour parser la date avec le décalage horaire
        try {
            ZonedDateTime date = ZonedDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
            System.out.println("Date correctement parsée: " + date);
        } catch (Exception e) {
            System.out.println("Erreur dans le parsing de la date: " + e.getMessage());
        }
    }



    @Override
    public String toString() {
        return "Flight{" +
                "airLineCode='" + airLineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", number=" + number +
                ", departureIATA='" + departureIATA + '\'' +
                '}';
    }

    // Méthode getArrival()
    public LocalDateTime getArrival() {

        return arrivalTime;
    }

    // Méthode getDeparture()
    public LocalDateTime getDeparture() {

        return departureTime;
    }
    public String getDepartureIATA() {

        return departureIATA;
    }

}