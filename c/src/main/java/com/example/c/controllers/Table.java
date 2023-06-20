package com.example.c.controllers;

import com.example.c.FX.CloseAction;
import com.example.c.FX.StudyGroupTable;
import com.example.c.FX.Translation;
import com.example.c.Handler.RequestHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.example.main.CommandFactory;
import org.example.main.Response;
import org.example.main.TypeOfCommand;
import org.example.models.*;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class Table implements Initializable, CloseAction {
    private int idbufer;
    private Stack<StudyGroup> collection;
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
    private TableColumn<StudyGroup, String> author;

    @FXML
    private TableColumn<StudyGroup, ZonedDateTime> date;

    @FXML
    private Button filter;

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
    private TableView<StudyGroup> studyView;

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

    private static ObservableList<Person> study = FXCollections.observableArrayList();

    private static Table instance;

    public static Table getInstance() {
        if (instance == null) instance = new Table();
        return instance;
    }

    public void loadCollection(){
        CommandFactory commandFactory = new CommandFactory(TypeOfCommand.show, (ArrayList<String>) null);
        Response response = RequestHandler.getInstance().send(commandFactory);
        setCollection(response.getStackOfStudyGroups());
    }
    public void setCollection(Stack<StudyGroup> collection) {
        this.collection = collection;
        if (collection != null) {
            for (StudyGroup studyGroup : collection) {
                System.out.println(studyGroup.toString());
            }
            studyView.setItems(FXCollections.observableArrayList(collection));

//            always sort TableView by id (by default), if i want to bypass the TreeSet sorting
//            idColumn.setSortType(TableColumn.SortType.ASCENDING);
//            table.getSortOrder().add(idColumn);

            studyView.refresh();
        }
    }
    public static ObservableList<Person> getPerson() {
        return study;
    }

    @FXML
    void addClick(){
        ProxyController.changeScene(new Stage(), "addTable.fxml");
//            Stage stage = (Stage) add.getScene().getWindow();
//            Platform.runLater(() -> ProxyController.changeScene(stage, "addTable.fxml"));
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
    void filterClick(MouseEvent event) {
        ProxyController controller = new ProxyController(AddController.class);
        List<StudyGroup> filtered = new LinkedList<>(StudyGroupTable.getStudy());
        if (idbufer > 0) {
            filtered = filtered.stream().filter(d -> d.getId() == idbufer).toList();
        }
        if (((TextField) controller.getField("name")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getName().equals(name.getText())).toList();
        }
        if (((ChoiceBox<String>) controller.getField("semester")).getValue() != null) {
            filtered = filtered.stream().filter(d -> d.getSemesterEnum().equals(
                    Semester.values()[((ChoiceBox<String>) controller.getField("semester")).getSelectionModel().getSelectedIndex()].getSemester())).toList();
        }
        if (((TextField) controller.getField("X")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getCoordinates().getX() == Double.parseDouble(coordinateX.getText())).toList();
        }
        if (((TextField) controller.getField("Y")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getCoordinates().getY() == Integer.parseInt(coordinateY.getText())).toList();
        }
        if (((TextField) controller.getField("count")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getStudentsCount() == Integer.parseInt(count.getText())).toList();
        }
        if (((ChoiceBox<String>) controller.getField("form")).getValue() != null) {
            filtered = filtered.stream().filter(d -> d.getFormOfEducation().equals(
                    FormOfEducation.values()[((ChoiceBox<String>) controller.getField("form")).getSelectionModel().getSelectedIndex()].getForm())).toList();
        }
        if (((TextField) controller.getField("AdminName")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getName().equals(AdminName.getText())).toList();
        }
        if (((TextField) controller.getField("AdminWeight")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getStudentsCount() == Integer.parseInt(AdminWeight.getText())).toList();
        }
        if (((TextField) controller.getField("AdminPassport")).getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getStudentsCount() == Integer.parseInt(AdminPassport.getText())).toList();
        }
        if (((ChoiceBox<String>) controller.getField("сolor")).getValue() != null) {
            filtered = filtered.stream().filter(d -> d.getFormOfEducation().equals(
                    Color.values()[((ChoiceBox<String>) controller.getField("сolor")).getSelectionModel().getSelectedIndex()].getColor())).toList();
        }

        currentList = FXCollections.observableArrayList(filtered);
        studyView.setItems(currentList);
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollection()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
//        currentList = StudyTable.getStudy();
//        loginText.setText(StaticData.getData().getLogin());

    }

    @FXML
    void delClick() {
        ProxyController controller = new ProxyController(AddController.class);
        TextField[] fields = {controller.getField("name"), controller.getField("count"),controller.getField("AdminName"), controller.getField("AdminWeight") , controller.getField("AdminPassport") , controller.getField("AdminBirthday")  , controller.getField("X") , controller.getField("Y")};
        for (TextField field: fields) {
            field.setText("");
        }
        ((ChoiceBox<String>) controller.getField("color")).setValue(null);
        ((ChoiceBox<String>) controller.getField("semester")).setValue(null);
        ((ChoiceBox<String>) controller.getField("form")).setValue(null);
        idbufer = 0;
        currentList = StudyGroupTable.getStudy();
        studyView.setItems(currentList);
    }

    @FXML
    private Button del;
}
