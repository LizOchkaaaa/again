package com.example.c.controllers;
import com.example.c.ProxyController;
import com.example.c.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.org.example.models.StudyGroup;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
public class Table implements Initializable, CloseAction {
    private final ProxyController controller = new ProxyController(RegistrationController.class);
    private ObservableList<StudyGroup> currentList;
    @FXML
    private TableColumn<StudyGroup, ZonedDateTime> AdminBirthday;

    @FXML
    private TableColumn<StudyGroup, String> AdminColour;

    @FXML
    private TableColumn<StudyGroup, String> AdminName;

    @FXML
    private TableColumn<StudyGroup, String> AdminPassport;

    @FXML
    private TableColumn<StudyGroup, Integer> AdminWeight;

    @FXML
    private Button add;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<StudyGroup, Double> coordinateX;

    @FXML
    private TableColumn<StudyGroup, Integer> coordinateY;

    @FXML
    private TableColumn<StudyGroup, Integer> count;

    @FXML
    private Button executeScript;

    @FXML
    private Label exit;

    @FXML
    private TableColumn<StudyGroup, String> formOfEducation;

    @FXML
    private Button help;

    @FXML
    private TableColumn<StudyGroup, Integer> id;

    @FXML
    private Button info;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private Label loginText;

    @FXML
    private TableColumn<StudyGroup, String> name;

    @FXML
    private Button removeGreater;

    @FXML
    private Button removeId;

    @FXML
    private Button reorder;

    @FXML
    private TableColumn<StudyGroup, String> semester;

    @FXML
    private Button start;

    @FXML
    private Button sumStudents;

    @FXML
    private Button update;

    private static ObservableList<com.example.c.models.Person> study = FXCollections.observableArrayList();

    private static Table instance;

    public static Table getInstance() {
        if (instance == null) instance = new Table();
        return instance;
    }

    public static ObservableList<Person> getPerson() {
        return study;
    }

    @FXML
    void addClick() {


    }

    @FXML
    void clearClick() {

    }

    @FXML
    void executeScriptClick() {

    }

    @FXML
    void exitFromTable() {

    }

    @FXML
    void helpClick(MouseEvent event) {

    }

    @FXML
    void infoClick(MouseEvent event) {

    }

    @FXML
    void removeGreaterClick(MouseEvent event) {

    }

    @FXML
    void removeIdClick(MouseEvent event) {

    }

    @FXML
    void reorderClick(MouseEvent event) {

    }

    @FXML
    void startClick(MouseEvent event) {
        ProxyController.changeScene(new Stage(), "start.fxml");

    }

    @FXML
    void sumClick(MouseEvent event) {

    }

    @FXML
    void updateClick(MouseEvent event) {

    }
    private final EventHandler<WindowEvent> closeEvent = event -> {
        System.out.println("ttt");
//        StaticData.getData().getConnection().close();
//        Connection.stop();
    };
    @Override
    public EventHandler<WindowEvent> close() {
        return closeEvent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.setVisible(false);
        ProxyController.setController(Table.class, this);
//        currentList = StudyTable.getStudy();
//        loginText.setText(StaticData.getData().getLogin());
//        new NodeManager().boxInitialize(Table.class, languages);

    }
}
