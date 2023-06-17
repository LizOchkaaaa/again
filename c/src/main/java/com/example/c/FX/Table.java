package com.example.c.FX;
import com.example.c.controllers.NewLoginController;
import com.example.c.controllers.ProxyController;
import com.example.c.controllers.RegistrationController;
import com.example.c.models.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
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
    private TableColumn<StudyGroup, String> AdminColor;

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
    private Button script;

    @FXML
    private Label exit;

    @FXML
    private Button help;
    @FXML
    private Button exitt;

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
    private Button start;

    @FXML
    private Button sumStudents;

    @FXML
    private Button update;

    @FXML
    private TableColumn<StudyGroup, String> form;

    @FXML
    private TableColumn<StudyGroup, String>  semester;

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
        Stage stage = (Stage) add.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "addTable.fxml"));
    }


    @FXML
    void exitClick() {
        Stage stage = (Stage) exitt.getScene().getWindow();
        Platform.runLater(() -> ProxyController.changeScene(stage, "registration.fxml"));
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
        ProxyController.setController(Table.class, this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new Translation(Table.class).changeLanguage(null);
        languages.setOnAction(new Translation(Table.class)::changeLanguage);
//        currentList = StudyTable.getStudy();
//        loginText.setText(StaticData.getData().getLogin());

    }
}
