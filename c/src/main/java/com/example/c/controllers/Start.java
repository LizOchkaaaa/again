package com.example.c.controllers;

import com.example.c.ProxyController;
import com.example.c.models.Person;
import com.example.c.models.StudyGroup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start implements Initializable {
    @FXML
    private AnchorPane visual;
    protected final ExecutorService service = Executors.newCachedThreadPool();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(Start.class, this);
        Table.getPerson().forEach(Person::start);
    }
}
