package com.example.tp_java_s9_tripodi;

import java.time.LocalDateTime;

public class Aeroport {
    private String Name;
    private String IATA;
    private String country;
    private double latitude;
    private double longitude;

    public Aeroport(String arrivalIATA) {
    }

    public double getLatitude() {
        return latitude;
    }

    public String getIATA() {
        return IATA;
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "Name='" + Name + '\'' +
                ", IATA='" + IATA + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public Aeroport (String n, String code, double lat, double lon, String country){
        this.Name=n;
        this.IATA=code;
        this.latitude=lat;
        this.longitude=lon;
        this.country=country;
    }

    public static class Flight {

        private Aeroport departure;
        private Aeroport arrival;
        private String airlineName;
        private String airLineCode;
        private int number;
        private LocalDateTime arrivalTime;

        public Aeroport getDeparture() {
            return departure;
        }

        private LocalDateTime departureTime;

        public Aeroport getArrival() {
            return arrival;
        }

        public Flight(Aeroport departure, Aeroport arrival, String airlineName, String airLineCode, int number, LocalDateTime arrivalTime, LocalDateTime departureTime) {
            this.departure = departure;
            this.arrival = arrival;
            this.airlineName = airlineName;
            this.airLineCode = airLineCode;
            this.number = number;
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }

        @Override
        public String toString() {
            return "com.example.tp_java_s9_tripodi.Aeroport.Flight{" +
                    "departure=" + departure +
                    ", arrival=" + arrival +
                    ", airline='" + airlineName + '\'' +
                    ", number=" + number +
                    ", arrivalTime=" + arrivalTime +
                    ", departureTime=" + departureTime +
                    '}';
        }
    }

    public static void main (String args[]){
        Aeroport a1 = new Aeroport("Cergy","CY",45,3,"FRANCE"); // Test sur Cergy
        System.out.println(a1);
    }
}