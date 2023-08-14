module com.georgiancollege.test2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.georgiancollege.test2 to javafx.fxml;
    exports com.georgiancollege.test2;
}