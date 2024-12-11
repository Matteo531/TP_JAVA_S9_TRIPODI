package com.example.tp_java_s9_tripodi;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Earth extends Group {
    private ArrayList<Sphere> yellowSpheres;  // Liste pour stocker les sphères jaunes
    private Rotate ry = new Rotate();  // Rotation pour l'animation de la Terre
    private Sphere sph;  // Représentation de la Terre
    private Sphere redSphere;  // Sphère rouge pour indiquer un aéroport sélectionné
    private boolean needUpdate = false;  // Indicateur pour les mises à jour de l'affichage
    private World world;  // Référence à l'objet World

    // Constructeur avec un paramètre World
    public Earth(World world) {
        this.world = world;  // Initialiser la référence à World
        yellowSpheres = new ArrayList<>();
        sph = new Sphere(300);  // Rayon de la Terre

        // Appliquer la texture de la Terre
        PhongMaterial skin = new PhongMaterial();
        javafx.scene.image.Image earthImage = new javafx.scene.image.Image(
                getClass().getResource("/com/example/tp_java_s9_tripodi/earth_lights_4800.png").toExternalForm()
        );
        skin.setDiffuseMap(earthImage);
        skin.setSelfIlluminationMap(earthImage);
        sph.setMaterial(skin);

        // Ajouter la Terre au groupe et configurer la rotation
        this.getChildren().add(sph);
        this.getTransforms().add(ry);

        // Animation pour faire tourner la Terre
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ry.setAxis(new Point3D(0, 1, 0));  // Rotation autour de l'axe Y
                ry.setAngle(now / 100000000);  // Angle de rotation (valeur ajustée)
                if (needUpdate) {
                    needUpdate = false;
                    updateYellowSpheres();
                }
            }
        };
        animationTimer.start();
    }

    // Créer une sphère pour représenter un point donné sur la Terre (en fonction des coordonnées géographiques)
    public Sphere createSphere(Aeroport a, Color color) {
        return createSphere(a.getLatitude(), a.getLongitude(), color);
    }

    // Créer une sphère avec latitude et longitude
    public Sphere createSphere(double latitude, double longitude, Color color) {
        PhongMaterial material = new PhongMaterial();
        material.setSpecularColor(color);
        material.setDiffuseColor(color);

        Sphere sphere = new Sphere(5);  // Rayon de la sphère
        sphere.setMaterial(material);

        sphere.setTranslateZ(-sph.getRadius());  // Placer sur la surface de la Terre

        // Rotation selon la longitude
        Rotate rotateLongitude = new Rotate(-longitude, Rotate.Y_AXIS);
        sphere.getTransforms().add(rotateLongitude);

        // Rotation selon la latitude
        Rotate rotateLatitude = new Rotate(-latitude * 60.0 / 90.0, Rotate.X_AXIS);
        sphere.getTransforms().add(rotateLatitude);

        return sphere;
    }

    // Afficher une sphère rouge pour un aéroport donné
    public void displayRedSphere(Aeroport a) {
        if (redSphere != null) {
            this.getChildren().remove(redSphere);  // Supprimer la sphère précédente si elle existe
        }
        redSphere = createSphere(a, Color.RED);
        this.getChildren().add(redSphere);

        // Interroger l'API et afficher les sphères jaunes
        displayYellowSpheres(a);
    }

    // Interroger l'API et afficher les sphères jaunes
    private void displayYellowSpheres(Aeroport a) {
        String accessKey = "73c01d9141ea4c4594a8525812d1a79a";  // Clé API
        String jsonResponse = ApiRequest.fetchFlightData(accessKey, a.getIATA());

        if (jsonResponse != null) {
            JsonFlightFiller filler = new JsonFlightFiller(jsonResponse, world);  // Utilisation de l'objet world ici

            for (Flight flight : filler.getFlights()) {
                // Utilisation de la méthode findByCode de World pour trouver l'aéroport de départ
                Aeroport departureAirport = world.findByCode(flight.getDepartureIATA());
                if (departureAirport != null) {
                    Sphere yellowSphere = createSphere(departureAirport, Color.YELLOW);
                    yellowSpheres.add(yellowSphere);
                    this.getChildren().add(yellowSphere);
                }
            }
        }
    }

    // Mettre à jour les sphères jaunes en cas de modification
    private void updateYellowSpheres() {
        // Supprimer les anciennes sphères jaunes
        for (Sphere sphere : yellowSpheres) {
            this.getChildren().remove(sphere);
        }
        yellowSpheres.clear();
    }

    // Afficher un réseau de sphères bleues pour tester l'affichage
    public void displayBlueSphere() {
        for (int latitude = -90; latitude < 90; latitude += 20) {
            for (int longitude = -180; longitude < 180; longitude += 20) {
                Sphere sphere = createSphere(latitude, longitude, Color.BLUE);
                this.getChildren().add(sphere);
            }
        }
    }
}
