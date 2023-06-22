package com.example.c.FX;

import com.example.c.models.Color;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Animation {
    private com.example.c.models.Color color;
    private AnchorPane main;
    private ExecutorService service;

    private final TranslateTransition goTransition = new TranslateTransition();
    private final ImageView image = new ImageView();

    private AtomicBoolean work = new AtomicBoolean(true);

    public Animation(com.example.c.models.Color color) {
        this.color = color;
    }

    private double x;
    private double y;


    public void start() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Start().service.submit(() -> {
            initialize();
            image.setTranslateX(x);
            image.setTranslateY(y);
            image.setFitWidth(300);
            image.setFitHeight(250);

            main.getChildren().add(image);
            goTransition.setNode(image);

            while(work.get()) {
                setImage();
                run();
            }
        });
    }

    public void run() {
        if (goTransition.getStatus() == javafx.animation.Animation.Status.RUNNING) {
            return;
        }

        goTransition.setDuration(Duration.seconds(3));
        if (x == -65) {
            image.setScaleX(1);
            goTransition.setToX(738);
            goTransition.setToY(y);
            x = 738;
        } else {
            image.setScaleX(-1);
            goTransition.setToX(-65);
            goTransition.setToY(y);
            x = -65;
        }
        goTransition.play();
    }

    public void remove() {
        work.set(false);
        main.getChildren().remove(image);
    }

    private void setImage() {
        try {
            image.setImage(new Image(getClass().getResource("/img/person" + color.getColor() + ".png").toURI()
                    .toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        double[] xCoor = {-65, 738};
        x = xCoor[(int) (Math.random()*2)];
        y = Math.random() * 530;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
