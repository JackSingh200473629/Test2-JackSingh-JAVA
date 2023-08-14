module com.georgiancollege.test2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.georgiancollege.test2 to javafx.fxml, com.google.gson;
    exports com.georgiancollege.test2;
}