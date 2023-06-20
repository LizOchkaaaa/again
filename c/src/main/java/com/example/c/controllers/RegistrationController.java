package com.example.c.controllers;
import com.example.c.FX.Registration;
import com.example.c.FX.Translation;
import com.example.c.FlowText;
import com.example.c.Object.AlertUtility;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controls all nodes in application
 */
public class RegistrationController implements Initializable {

    @FXML
    private Button enter;
    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label registration;

    @FXML
    @FlowText
    private Label passwordWarn;

    @FXML
    private Label welcome;

    @FXML
    @FlowText
    private Label loginWarn;


    @FXML
    void validate(MouseEvent event) {
        loginWarn.setText("");
        passwordWarn.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(RegistrationController.class, this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new Translation(RegistrationController.class).changeLanguage(null);
        languages.setOnAction(new Translation(RegistrationController.class)::changeLanguage);
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
        Registration registration1 = new Registration("existedUser", Translation.getLocale());
        if (registration1.register()) {

            Stage stage = (Stage) enter.getScene().getWindow();
            Platform.runLater(() -> ProxyController.changeScene(stage, "table.fxml"));
        }
    }
}