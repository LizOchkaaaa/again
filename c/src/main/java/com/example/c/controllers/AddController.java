package com.example.c.controllers;

import com.example.c.FX.Translation;
import com.example.c.Fields.FieldFetcher;
import com.example.c.Handler.RequestHandler;
import com.example.c.Object.CommandManager;
import com.example.c.validatorClient.Validation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.main.CommandFactory;
import org.example.main.Request;
import org.example.main.Response;
import org.example.main.TypeOfCommand;
import org.example.models.Person;
import org.example.models.StudyGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private Integer id;
    @FXML
    private TextField AdminBirthday;

    @FXML
    private TextField AdminName;

    @FXML
    private TextField AdminPassport;

    @FXML
    private TextField AdminWeight;

    @FXML
    private TextField count;

    @FXML
    private TextField X;

    @FXML
    private TextField Y;

    @FXML
    private Button add;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> color;

    @FXML
    private ChoiceBox<String> form;

    @FXML
    private ChoiceBox<String> semester;

    private static ObservableList<Person> study = FXCollections.observableArrayList();

    @FXML
    void addCollection() {
        try {
            if (id == null){
                id = 0;
            }
            StudyGroup studyGroup = new Validation().getStudyGroup(id, AddController.class);
            CommandFactory commandFactory;
            if (id == 0){
             commandFactory = new CommandFactory(TypeOfCommand.add, (ArrayList<String>) null);
            }
            else {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(id.toString());
                commandFactory = new CommandFactory(TypeOfCommand.update, arrayList);
            }
            Response response = RequestHandler.getInstance().send(commandFactory,studyGroup);

        }catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
        }
    @FXML
    private Button exiting;

    @FXML
    private AnchorPane language;

    @FXML
    private ChoiceBox<String> languages;



    @FXML
    void backClick() {
        Stage stage = (Stage) exiting.getScene().getWindow();
        ProxyController.changeScene(stage, "table.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(AddController.class,this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new com.example.c.FX.ChoiceBox().boxInitialize(AddController.class, languages , color, semester, form);
        languages.setOnAction(new Translation(AddController.class)::changeLanguage);
    }
    public void setId(Integer aId){
        id = aId;
    }
}
