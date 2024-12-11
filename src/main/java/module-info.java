module com.example.tp_java_s9_tripodi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires org.glassfish.java.json;

    opens com.example.tp_java_s9_tripodi to javafx.fxml;
    exports com.example.tp_java_s9_tripodi;
}