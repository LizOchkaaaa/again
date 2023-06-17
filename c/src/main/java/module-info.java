module com.example.c {
    requires org.example.commons;
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example.c to javafx.fxml;
    exports com.example.c;
    exports com.example.c.controllers;
    opens com.example.c.controllers to javafx.fxml;
    exports com.example.c.FX;
    opens com.example.c.FX to javafx.fxml;
    exports com.example.c.Object;
    opens com.example.c.Object to javafx.fxml;
    exports com.example.c.Fields;
    opens com.example.c.Fields to javafx.fxml;
    exports com.example.c.Interface;
    opens com.example.c.Interface to javafx.fxml;
}