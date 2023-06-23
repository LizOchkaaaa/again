package com.example.c.controllers;

import com.example.c.FX.*;
import com.example.c.Handler.RequestHandler;
import com.example.c.Object.AlertUtility;
import com.example.c.validatorClient.Validation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.example.main.CommandFactory;
import org.example.main.Response;
import org.example.main.Session;
import org.example.main.TypeOfCommand;
import org.example.models.*;

import java.net.URL;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Table implements Initializable, CloseAction {
    private int idbufer;
    private Stack<StudyGroup> oldCollection;
    private static Stack<com.example.c.models.StudyGroup> collection;
    private Timestamp dateBuffer;

    private Map<String, javafx.scene.paint.Color> clientColorMap = new HashMap<>();
    @FXML
    private TableView<StudyGroup> studyView;
    private ObservableList<StudyGroup> currentList;
    @FXML
    private TableColumn<StudyGroup, String> AdminBirthday;

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
    private TableColumn<StudyGroup, String> date;

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
    private Label exit;

    @FXML
    private Button exitt;

    @FXML
    private TableColumn<StudyGroup, Integer> id;

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
    private TableColumn<StudyGroup, String> semester;

//    private static ObservableList<com.example.c.models.Person> study = FXCollections.observableArrayList();

    private static Table instance;

    public static Table getInstance() {
        if (instance == null) instance = new Table();
        return instance;
    }

    public void loadCollection() {
        CommandFactory commandFactory = new CommandFactory(TypeOfCommand.show, (ArrayList<String>) null);
        Response response = RequestHandler.getInstance().send(commandFactory);
        oldCollection = response.getCollection();
        setCollection(response.getStackOfStudyGroups());
    }

    public void setCollection(Stack<StudyGroup> collection) {
        change(collection);
        if (collection != null) {
            for (StudyGroup studyGroup : collection) {
                System.out.println(studyGroup.toString());
            }
            studyView.setItems(FXCollections.observableArrayList(collection));
            studyView.refresh();
        }
    }

    public static Stack<com.example.c.models.StudyGroup> getPerson() {
        return collection;
    }

    @FXML
    void addClick() {
        ProxyController.changeScene(new Stage(), "addTable.fxml");
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
    void removeGreaterClick(MouseEvent event) {

    }

    @FXML
    void removeIdClick(MouseEvent event) {

    }

    @FXML
    void startClick(MouseEvent event) {
        ProxyController.changeScene(new Stage(), "start.fxml");

    }

    @FXML
    void sumClick(MouseEvent event) {

    }

    @FXML
    void updateClick() {
        try {
            StudyGroup studyGroup = studyView.getSelectionModel().getSelectedItem();
            if (studyGroup != null){
                AddTable addTable = new AddTable();
                addTable.setId(studyGroup.getId());
            }else {
                AlertUtility.infoAlert("Please, select any StudyGroup to update it");
            }
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую

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

        id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        coordinateX.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCoordinates().getX()).asObject());
        coordinateY.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCoordinates().getY()).asObject());
        date.setCellValueFactory(cellData -> new SimpleStringProperty(getDate(cellData.getValue().getCreationDate())));
        count.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStudentsCount()).asObject());
        form.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormOfEducation().getForm()));
        semester.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSemesterEnum().getSemester()));
        AdminName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGroupAdmin().getName()));
        AdminBirthday.setCellValueFactory(cellData -> new SimpleStringProperty(getDate(cellData.getValue().getGroupAdmin().getBirthday())));
        AdminWeight.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getGroupAdmin().getWeight()).asObject());
        AdminPassport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGroupAdmin().getPassportID()));
        AdminColor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGroupAdmin().getHairColor().getColor()));
        author.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));


        id.setPrefWidth(20);
        name.setPrefWidth(50);
        coordinateX.setPrefWidth(40);
        coordinateY.setPrefWidth(40);
        date.setPrefWidth(50);
        count.setPrefWidth(30);
        form.setPrefWidth(50);
        semester.setPrefWidth(50);
        AdminName.setPrefWidth(50);
        AdminBirthday.setPrefWidth(50);
        AdminWeight.setPrefWidth(30);
        AdminPassport.setPrefWidth(30);
        AdminColor.setPrefWidth(50);
        author.setPrefWidth(50);

        String user = RequestHandler.getInstance().getSession().getName();
        loginText.setText(user);
        loadOwnershipMap();

        studyView.setRowFactory(tv -> new TableRow<StudyGroup>() {
            @Override
            public void updateItem(StudyGroup studyGroup, boolean empty) {
                super.updateItem(studyGroup, empty);
                if (studyGroup == null) {
                    setStyle("");
                } else {
                    javafx.scene.paint.Color color1 = clientColorMap.get(studyGroup.getAuthor());
                    String rgb = String.format("#%02X%02X%02X",
                            (int) (color1.getRed() * 255),
                            (int) (color1.getGreen() * 255),
                            (int) (color1.getBlue() * 255));
                    setStyle("-fx-border-color: " + rgb + ";");
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> loadCollection()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        filter.textProperty().addListener((observable , oldValue , newValue) -> {
                    if (newValue != null && !newValue.isEmpty()) {
//                        String selectedValue = .getSelectedItem();
//                        filterCities(selectedValue, newValue);
                    } else {
                        // If the TextField is empty, show all cities
                        studyView.setItems(FXCollections.observableArrayList(oldCollection));
                    }});

        currentList = StudyGroupTable.getStudy();
        loginText.setText(StaticData.getData().getLogin());
    }

        private void loadOwnershipMap() {
            Session session = RequestHandler.getInstance().getSession();
            CommandFactory commandFactory = new CommandFactory(TypeOfCommand.valueOf("show"), (String) null);
            Response response = RequestHandler.getInstance().send(commandFactory);
            change(response.getCollection());
            if (collection != null) {
                for (com.example.c.models.StudyGroup studyGroup : collection) {
                    if (studyGroup.getAuthor().equals(session.getName())) {
                        clientColorMap.put(studyGroup.getAuthor(), javafx.scene.paint.Color.GREEN);
                    } else {
                        if (!clientColorMap.containsKey(studyGroup.getAuthor())) {
                            javafx.scene.paint.Color randomColor = javafx.scene.paint.Color.color(Math.random(), Math.random(), Math.random());
                            clientColorMap.put(studyGroup.getAuthor(), randomColor);
                        }
                    }
                }
            }
        }

    private String getDate(Date date) {
        if (date == null) return "null";
        DateFormat formatter = DateFormat.getDateInstance();
        return formatter.format(date);
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
    public void change(Stack<StudyGroup> aCollection) {
        collection = new Stack<com.example.c.models.StudyGroup>();
        for (StudyGroup aStudyGroup : aCollection) {
            com.example.c.models.StudyGroup studyGroup = new com.example.c.models.StudyGroup(aStudyGroup.getId(),
                    aStudyGroup.getName(), new com.example.c.models.Coordinates(aStudyGroup.getCoordinates().getX(), aStudyGroup.getCoordinates().getY()), aStudyGroup.getCreationDate(), aStudyGroup.getStudentsCount(),
                    com.example.c.models.FormOfEducation.valueOf(aStudyGroup.getFormOfEducation().getForm()), com.example.c.models.Semester.valueOf(aStudyGroup.getSemesterEnum().getSemester()), new com.example.c.models.Person(aStudyGroup.getGroupAdmin().getName(), aStudyGroup.getGroupAdmin().getBirthday(),
                    aStudyGroup.getGroupAdmin().getWeight(), aStudyGroup.getGroupAdmin().getPassportID(),
                    com.example.c.models.Color.valueOf(aStudyGroup.getGroupAdmin().getHairColor().getColor())), aStudyGroup.getAuthor());
            collection.add(studyGroup);
        }
    }
    @FXML
    protected void onUpdateClick(MouseEvent e){

    }
    public com.example.c.models.StudyGroup changeStudyGroup(StudyGroup aStudyGroup){
        com.example.c.models.StudyGroup studyGroup = new com.example.c.models.StudyGroup(aStudyGroup.getId(),
                aStudyGroup.getName(), new com.example.c.models.Coordinates(aStudyGroup.getCoordinates().getX(), aStudyGroup.getCoordinates().getY()), aStudyGroup.getCreationDate(), aStudyGroup.getStudentsCount(),
                com.example.c.models.FormOfEducation.valueOf(aStudyGroup.getFormOfEducation().getForm()), com.example.c.models.Semester.valueOf(aStudyGroup.getSemesterEnum().getSemester()), new com.example.c.models.Person(aStudyGroup.getGroupAdmin().getName(), aStudyGroup.getGroupAdmin().getBirthday(),
                aStudyGroup.getGroupAdmin().getWeight(), aStudyGroup.getGroupAdmin().getPassportID(),
                com.example.c.models.Color.valueOf(aStudyGroup.getGroupAdmin().getHairColor().getColor())), aStudyGroup.getAuthor());
        return studyGroup;
    }

    @FXML
    private Button del;
}
