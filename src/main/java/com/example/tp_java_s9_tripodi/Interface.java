package com.example.tp_java_s9_tripodi;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Interface extends Application {
    Translate tz = new Translate();
    private double mousePosX;
    private double mousePosY;

    @Override
    public void start(Stage primaryStage) throws Exception {
        World w = new World("C:/Users/Matteo/IdeaProjects/TP_JAVA_S9_TRIPODI/src/main/resources/com/example/tp_java_s9_tripodi/airport-codes_no_comma.csv"); // Utilise le chemin absolue car problème avec le ralatif
        // ArrayList<com.example.tp_java_s9_tripodi.Aeroport.Flight> listOfFlight = new ArrayList<com.example.tp_java_s9_tripodi.Aeroport.Flight>();

        primaryStage.setTitle("So world");

        Earth earth = new Earth();
        Scene ihm = new Scene(earth, 800, 600,true);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(3000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);

        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                tz.setZ((event.getSceneY() - mousePosY)*0.1);
                camera.getTransforms().add(tz);
            }
        });

        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton()== MouseButton.SECONDARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    Point2D click=pickResult.getIntersectedTexCoord();
                    double longitude=360*(click.getX()-0.5);
                    double latitude=180*(0.5-click.getY());
                    Aeroport a = w.findNearestAirport(longitude,latitude);
                    earth.displayRedSphere(a);
                    //earth.displayBlueSphere(); // Test d'affichage
                    System.out.println("x="+longitude+" y ="+latitude);
                    System.out.println(a);

                }
            }
        });



        primaryStage.setScene(ihm);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}