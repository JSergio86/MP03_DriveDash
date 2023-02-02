module com.example.mp03_drivedash {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mp03_drivedash to javafx.fxml;
    exports com.example.mp03_drivedash;
}