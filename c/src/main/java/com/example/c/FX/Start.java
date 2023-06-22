package com.example.c.FX;

import com.example.c.controllers.ProxyController;
import com.example.c.controllers.Table;
import com.example.c.models.Person;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start implements Initializable , CloseAction {
    @FXML
    private AnchorPane visual;
    protected final ExecutorService service = Executors.newCachedThreadPool();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProxyController.setController(Start.class, this);
        Table.getPerson().forEach(Person::start);
    }

    private final EventHandler<WindowEvent> closeEvent = event -> {
        Table.getPerson().forEach(d -> d.finish());
        service.shutdown();
        ProxyController.remove(Start.class);
    };

    @Override
    public EventHandler<WindowEvent> close() {
        return closeEvent;
    }
}
