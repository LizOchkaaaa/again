package com.example.c.controllers;
import com.example.c.Client;
import com.example.c.ProxyController;
import com.example.c.Registration;
import com.example.c.Translation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controls all nodes in application
 */
public class RegistrationController implements Initializable {

    @FXML
    private Button enter;
    @FXML
    private ChoiceBox<String> language;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label registration;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(RegistrationController.class, this);

        language.setItems(Translation.getAllLanguages());
        language.setValue(Translation.getLanguage());
        new Translation(RegistrationController.class).changeLanguage(null);
        language.setOnAction(new Translation(RegistrationController.class)::changeLanguage);
    }

    @FXML
    protected void registerClick() {
        Stage stage = (Stage) registration.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "register.fxml"));
    }

//    @FXML
//    protected void cancelConnection(MouseEvent event) {
//        new Registration().cancel();
////        Connection.stop();
//    }

    @FXML
    protected void field(MouseEvent event) {
        login.setText("");
        password.setText("");
    }

    @FXML
    void enterClick() {
//        Registration registration1 = new Registration("existedUser", Translation.getLocale());
//        registration1.register();

        Stage stage = (Stage) enter.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "table.fxml"));
    }

}