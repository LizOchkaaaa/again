package com.example.c.controllers;

import com.example.c.ProxyController;
import com.example.c.models.Color;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Animation implements Initializable {
    private Color color;
    private AnchorPane main;
    private ExecutorService service;

    private final TranslateTransition goTransition = new TranslateTransition();
    private final ImageView image = new ImageView();

    private AtomicBoolean work = new AtomicBoolean(true);

    public Animation(Color color) {
        this.color = color;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = new ProxyController(Start.class).getField("visual");
        service = new ProxyController(Start.class).getField("service");
        work.set(true);
    }
}
