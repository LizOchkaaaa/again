package com.example.c.controllers;

import com.example.c.FX.NewLogin;
import com.example.c.FX.Translation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewLoginController implements Initializable {
    private final ProxyController controller = new ProxyController(NewLoginController.class);
    @FXML
    private Label label;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button start;

    @FXML
    private Button back;

    @FXML
    void startClick() {
        NewLogin newLogin = new NewLogin("newUser", Translation.getLocale());
        newLogin.registerNew();

        Stage stage = (Stage) start.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "registration.fxml"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(NewLoginController.class,this);

        new Translation(NewLoginController.class).changeLanguage(null);
    }

    @FXML
    void backClick() {
        Stage stage = (Stage) back.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "registration.fxml"));
    }

}
