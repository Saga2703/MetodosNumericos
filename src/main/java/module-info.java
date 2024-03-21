module com.example.metodosnumeros {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens com.example.metodosnumeros to javafx.fxml;
    exports com.example.metodosnumeros;
}
