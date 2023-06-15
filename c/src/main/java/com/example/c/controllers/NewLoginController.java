package com.example.c.controllers;

import com.example.c.CommandManager;
import com.example.c.ProxyController;
import com.example.c.Translation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.org.example.main.Session;
import main.org.example.main.TypeOfSession;

import java.net.URL;
import java.util.Arrays;
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
    void startClick() {
        NewLogin newLogin = new NewLogin("newUser", Translation.getLocale());
        newLogin.registerNew();

        Stage stage = (Stage) start.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "registration.fxml"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(NewLoginController.class,this);
    }
}
