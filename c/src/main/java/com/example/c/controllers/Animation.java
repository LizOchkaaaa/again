package com.example.c.controllers;

import com.example.c.ProxyController;
import com.example.c.models.Color;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URISyntaxException;
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
        try {
            Thread.sleep(100);

            service.submit(() -> {
                try {
                    ImageView imageView = new ImageView(new Image(getClass().getResource("/img/" + color + "person.png").toURI().toString()));
                } catch (URISyntaxException e) {
                    return;
                }
                image.setTranslateX(Math.random() * 803 - 65);
                image.setTranslateY(Math.random() * 458);
                image.setFitWidth(300);
                image.setFitHeight(250);
                Platform.runLater(() -> main.getChildren().add(image));

                int i = 0;
                while (work.get()) {
                    run();
                    if (i == 15) {
                        i = 0;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {throw new RuntimeException(e);}
                }
            });
        } catch (InterruptedException | NullPointerException e) {e.printStackTrace();}
    }

    public void run() {
        if (goTransition.getStatus() == javafx.animation.Animation.Status.RUNNING) {
            return;
        }
        double endX = Math.random() * 803 - 70;
        double endY = Math.random() * 458;

        goTransition.setDuration(Duration.seconds(3));
        goTransition.setNode(image);
        goTransition.setToX(endX);
        goTransition.setToY(endY);
        if (endX < 0) {
            image.setScaleX(-1);
        } else {
            image.setScaleX(1);
        }
        goTransition.play();
    }

    public void remove() {
        work.set(false);
        main.getChildren().remove(image);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }




}
