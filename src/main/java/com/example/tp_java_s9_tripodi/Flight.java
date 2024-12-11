package com.example.tp_java_s9_tripodi;

public class Flight {
    private String flightNumber;
    private String departureIATA;
    private String arrivalIATA;

    // Constructeur
    public Flight(String flightNumber, String departureIATA, String arrivalIATA) {
        this.flightNumber = flightNumber;
        this.departureIATA = departureIATA;
        this.arrivalIATA = arrivalIATA;
    }

    // 3 Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureIATA() {
        return departureIATA;
    }

    public String getArrivalIATA() {
        return arrivalIATA;
    }

    // Méthode toString pour faciliter l'affichage
    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureIATA='" + departureIATA + '\'' +
                ", arrivalIATA='" + arrivalIATA + '\'' +
                '}';
    }
}
