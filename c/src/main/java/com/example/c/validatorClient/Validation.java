package com.example.c.validatorClient;

import com.example.c.FX.ChoiceBox;
import com.example.c.FX.StaticData;
import com.example.c.FX.Translation;
import com.example.c.controllers.ProxyController;
import javafx.scene.control.TextField;
import org.example.models.*;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class Validation {
    public String string(TextField field) {
        String name = field.getText();
        if (name.length() > 0) {
            return name;
        }
        field.setText("");
        field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("nameEmpty"));
        new ChoiceBox().textFieldError(field);
        return null;
    }
    public Double doubleLabel(TextField field){
        try {
            return Double.parseDouble(field.getText());
        }catch (NumberFormatException e){
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("nameEmpty"));
            new ChoiceBox().textFieldError(field);

        }
        return null;
    }
    public Integer integer(TextField field) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("numberError"));
            new ChoiceBox().textFieldError(field);
        }
        return null;
    }
    public Integer choiceBox(javafx.scene.control.ChoiceBox<String> choiceBox) {
        if (choiceBox.getValue() != null) {
            return choiceBox.getSelectionModel().getSelectedIndex();
        }
        return null;
    }
    public ZonedDateTime birthday(TextField field){
        try {
            String variable = field.getText();
            String[] args = variable.split("-");
            ZonedDateTime zdtWithZoneOffset = ZonedDateTime.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), 0, 0, 0, 0, ZoneId.systemDefault());
            if (variable != null && args.length == 3) {
                return zdtWithZoneOffset;
            }
        }catch (IndexOutOfBoundsException | DateTimeException | NullPointerException | NumberFormatException e) {
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("numberError"));
            new ChoiceBox().textFieldError(field);
        }
        return null;
    }

    public Object[] validate(Class<?> controllerClass) {
        Object[] elements = new Object[11];
        ProxyController controller = new ProxyController(controllerClass);
        elements[0] = string(controller.getField("name"));
        elements[1] = doubleLabel(controller.getField("X"));
        elements[2] = integer(controller.getField("Y"));
        elements[3] = choiceBox(controller.getField("semester"));
        elements[4] = integer(controller.getField("count"));
        elements[5] = choiceBox(controller.getField("form"));
        elements[6] = string(controller.getField("AdminName"));
        elements[7] = birthday(controller.getField("AdminBirthday"));
        elements[8] = integer(controller.getField("AdminWeight"));
        elements[9] = string(controller.getField("AdminPassport"));
        elements[10] = choiceBox(controller.getField("color"));
        return elements;
    }
    public StudyGroup getStudyGroup(Integer id, Class<?> controllerClass) {
        Object[] elements = validate(controllerClass);
        try {
            Arrays.stream(elements).filter(Objects::isNull).findFirst();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        return new StudyGroup(id, (String) elements[0], new Coordinates((Double) elements[1], (Integer) elements[2]),
                Semester.valueOf(Semester.values()[(int) elements[3]].getSemester()), (Integer) elements[4],
                FormOfEducation.valueOf(FormOfEducation.values()[(int) elements[5]].getForm()), new Person((String) elements[6], (ZonedDateTime) elements[7],
                (Integer) elements[8], (String) elements[9], Color.valueOf(Color.values()[(int) elements[10]].getColor())));

    }
}
