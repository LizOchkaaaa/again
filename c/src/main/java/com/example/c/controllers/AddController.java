package com.example.c.controllers;

import com.example.c.FX.Table;
import com.example.c.Fields.FieldFetcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.org.example.main.CommandFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private FieldFetcher fieldsValidator;
    private CommandFactory command;
    private byte[] bytes;
    @FXML
    private TextField AdminBirthday;

    @FXML
    private TextField AdminName;

    @FXML
    private TextField AdminPassport;

    @FXML
    private TextField AdminWeight;

    @FXML
    private TextField X;

    @FXML
    private TextField Y;

    @FXML
    private Button add;

    @FXML
    private TextField name;

    public AddController(FieldFetcher fieldsValidator) {
        this.fieldsValidator = fieldsValidator;
    }

    @FXML
    private javafx.scene.control.ChoiceBox<String> color;

    @FXML
    private ChoiceBox<String> form;

    @FXML
    private ChoiceBox<String> semester;

    @FXML
    void addCollection() {
//        try {
//            StudyGroup studyGroup = new FieldFetcher().getDragon(0, Table.class);
//            studyGroup.setCreation(new Timestamp(new Date().getTime()));
//            StaticData.getData().getConnection().sendRequest(bytes);
//        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    private Button exiting;

    @FXML
    private AnchorPane language;



    @FXML
    void backClick() {
        Stage stage = (Stage) exiting.getScene().getWindow();
        ProxyController.changeScene(stage, "table.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(AddController.class,this);

        new com.example.c.FX.ChoiceBox().boxInitialize(Table.class, color, semester, form);
    }
}
