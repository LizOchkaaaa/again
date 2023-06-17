package com.example.c.FX;
import com.example.c.FlowText;
import com.example.c.controllers.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import main.org.example.models.Color;
import main.org.example.models.FormOfEducation;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Class for translation texts to different languages using text from .properties files
 */
public class Translation {
    private static final Map<String, Locale> languages = new LinkedHashMap<>();
    private ProxyController controller;
    private static String language;

    public Translation(Class<?> clas) {
        controller = new ProxyController(clas);
    }

    public static void setLanguage(String language) {
        Translation.language = language;
    }

    public static String getLanguage() {
        return language;
    }

    static {
        languages.put("Русский", new Locale("ru", "RU"));
        languages.put("हिन्दी", new Locale("hi", "IN"));
        languages.put("Íslenska", new Locale("is", "IS"));
        languages.put("Svensk", new Locale("sv", "SE"));
    }

    public static Locale getLocale() {
        return languages.get(language);
    }

    public static ObservableList<String> getAllLanguages() {
        return FXCollections.observableArrayList(languages.keySet().stream().toList());
    }

    /**
     * Translate all texts in application
     *
     * @param event
     * @return
     */
    public void changeLanguage(ActionEvent event) {
        String[] bundles = {"Registration" , "Register" , "Table" , "Add"};
        try {
            setLanguage(((javafx.scene.control.ChoiceBox<String>) controller.getField("languages")).getValue());
        }catch (NullPointerException ignored){
        }

        Field[] fields = controller.getAllFields();
        Locale locale = getLocale();

        for (String bundle : bundles) {
            for (Field field : fields) {
                try {
                    String data = ResourceBundle.getBundle("properties." + bundle, locale).getString(field.getName());
                    if (field.getType() == TextField.class || field.getType() == PasswordField.class) {
                        TextInputControl textInputControl = (TextInputControl) field.get(controller.getControllerClass());
                        textInputControl.setPromptText(data);
                    } else if (field.getType() == Button.class || field.getType() == Label.class) {
                        Labeled label = (Labeled) field.get(controller.getControllerClass());
                        if (!field.isAnnotationPresent(FlowText.class) || label.getText().length() > 0) {
                            label.setText(data);
                        }

                    } else if (field.getType() == TableColumn.class) {
                        TableColumn<?, ?> tableColumn = (TableColumn<?, ?>) field.get(controller.getControllerClass());
                        tableColumn.setText(data);
                    }

                    changeColorLanguage(locale);
                    changeFormOfEducationLanguage(locale);
                } catch (MissingResourceException | IllegalAccessException | NullPointerException ignored) {
                }
            }
        }

    }

    public void changeColorLanguage(Locale locale) {
        String[] enums = new String[Color.values().length];
        ResourceBundle bundle = ResourceBundle.getBundle("properties.Table", locale);
        for (Color color : Color.values()) {
            enums[color.ordinal()] = bundle.getString(color.getColor());
        }

        javafx.scene.control.ChoiceBox<String> colors = controller.getField("colorChoice");
        int index = colors.getSelectionModel().getSelectedIndex();
        colors.getItems().setAll(enums);
        if (index >= 0) {
            colors.setValue(enums[index]);
        }
    }

    public void changeFormOfEducationLanguage(Locale locale) {
        String[] enums = new String[FormOfEducation.values().length];
        for (FormOfEducation type : FormOfEducation.values()) {
            enums[type.ordinal()] = ResourceBundle.getBundle("properties.Table", locale).getString(type.getType());
        }

        ChoiceBox<String> type = controller.getField("typeChoice");
        int index = type.getSelectionModel().getSelectedIndex();
        type.getItems().setAll(enums);
        if (index >= 0) {
            type.setValue(enums[index]);
        }
    }
}