module com.example.c {
    requires org.example.commons;
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example.c to javafx.fxml;
    exports com.example.c;
    exports com.example.c.controllers;
    opens com.example.c.controllers to javafx.fxml;
}