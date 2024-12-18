/*
public class ThreadScrapOnlineFlight extends Thread{
    ArrayList<com.example.tp_java_s9_tripodi.Aeroport.Flight> listOfFlight;
    Earth earth;
    Aeroport a;
    World w;

    public ThreadScrapOnlineFlight(Earth earth, Aeroport a, World w, ArrayList<com.example.tp_java_s9_tripodi.Aeroport.Flight> listOfFlight) {
        this.earth = earth;
        this.a = a;
        this.w = w;
        this.listOfFlight=listOfFlight;
    }

    @Override
    public void run() {
        super.run();
        try{
            listOfFlight.clear();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.aviationstack.com/v1/flights?access_key=cfaf27d3b7c76c08bafee49ddb0df72c&arr_iata=" + a.getIATA()))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonFlightFillerOracle json = new JsonFlightFillerOracle(response.body().toString(),w);

            listOfFlight = json.getList();
            json.displayFlight();
            earth.getFlightList().addAll(listOfFlight);
            earth.setNeedUpdate(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}

 */