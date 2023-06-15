package com.example.c;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Class for translation texts to different languages using text from .properties files
 */
public class Translation {
    private static final Map<String, Locale> languages = new LinkedHashMap<>();
    private static String language;
    private ProxyController controller;

    static {
        languages.put("Русский", new Locale("ru", "RU"));
        languages.put("हिन्दी", new Locale("en", "EN"));
        languages.put("Íslenska", new Locale("is", "ÍS"));
        languages.put("Svensk", new Locale("sv", "SV"));
    }


    public Translation(Class<?> clas) {
        controller = new ProxyController(clas);
    }

    public static void setLanguage(String language) {
        Translation.language = language;
    }

    public static String getLanguage() {
        return language;
    }

    /**
     * Translate all texts in application
     *
     * @param event
     */
    public void changeLanguage(ActionEvent event) {
        System.out.println("eee");
    }

//        String[] bundles = {"Registration", "Table"};
//        setLanguage(((ChoiceBox<String>) controller.getField("languages")).getValue());
//        Field[] fields = controller.getAllFields();
//        Locale locale = getLocale();
//
//        for (String bundle : bundles) {
//            for (Field field : fields) {
//                try {
//                    String data = ResourceBundle.getBundle("properties." + bundle, locale).getString(field.getName());
//                    if (field.getType() == TextField.class || field.getType() == PasswordField.class) {
//                        TextInputControl textInputControl = (TextInputControl) field.get(controller.getControllerClass());
//                        textInputControl.setPromptText(data);
//                    } else if (field.getType() == Button.class || field.getType() == Label.class) {
//                        Labeled label = (Labeled) field.get(controller.getControllerClass());
//                        if (!field.isAnnotationPresent(FlowText.class) || label.getText().length() > 0) {
//                            label.setText(data);
//                        }
//
//                    } else if (field.getType() == TableColumn.class) {
//                        TableColumn<?, ?> tableColumn = (TableColumn<?, ?>) field.get(controller.getControllerClass());
//                        tableColumn.setText(data);
//                    }
//
////                    changeColorLanguage(locale);
////                    changeTypeLanguage(locale);
////                    changeCharacterLanguage(locale);
//                } catch (MissingResourceException | IllegalAccessException | NullPointerException ignored) {
//                }
//            }
//        }
//    }


    public static Locale getLocale() {
        return languages.get(language);
    }

    public static ObservableList<String> getAllLanguages() {
        return FXCollections.observableArrayList(languages.keySet().stream().toList());
    }

//    public void changeColorLanguage(Locale locale) {
//        String[] enums = new String[Color.values().length];
//        ResourceBundle bundle = ResourceBundle.getBundle("properties.Table", locale);
//        for (Color color : Color.values()) {
//            enums[color.ordinal()] = bundle.getString(color.getColor());
//        }
//
//        ChoiceBox<String> colors = controller.getField("colorChoice");
//        int index = colors.getSelectionModel().getSelectedIndex();
//        colors.getItems().setAll(enums);
//        if (index >= 0) {
//            colors.setValue(enums[index]);
//        }
//    }

//    public void changeTypeLanguage(Locale locale) {
//        String[] enums = new String[DragonType.values().length];
//        for (DragonType type : DragonType.values()) {
//            enums[type.ordinal()] = ResourceBundle.getBundle("properties.Table", locale).getString(type.getType());
//        }
//
//        ChoiceBox<String> type = controller.getField("typeChoice");
//        int index = type.getSelectionModel().getSelectedIndex();
//        type.getItems().setAll(enums);
//        if (index >= 0) {
//            type.setValue(enums[index]);
//        }
//    }
//
//    public void changeCharacterLanguage(Locale locale) {
//        String[] enums = new String[DragonCharacter.values().length];
//        for (DragonCharacter character : DragonCharacter.values()) {
//            enums[character.ordinal()] = ResourceBundle.getBundle("properties.Table", locale).
//                    getString(character.getCharacter());
//        }
//
//        ChoiceBox<String> character = controller.getField("characterChoice");
//        int index = character.getSelectionModel().getSelectedIndex();
//        character.getItems().setAll(enums);
//        if (index >= 0) {
//            character.setValue(enums[index]);
//        }
//    }
}