package com.example.c.FX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.models.StudyGroup;

public class StudyGroupTable {
    private static ObservableList<StudyGroup> study = FXCollections.observableArrayList();
    public static ObservableList<StudyGroup> getStudy() {
        return study;
    }
}
