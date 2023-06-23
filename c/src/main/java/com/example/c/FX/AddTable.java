package com.example.c.FX;

import com.example.c.Client;
import com.example.c.controllers.AddController;
import com.example.c.controllers.ProxyController;
import com.example.c.controllers.Table;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class AddTable {
    private Stage stage;
    private Scene scene;
    private AddController controller;

    public AddTable(){
            URL fxmlLocation = Client.class.getResource("addTable.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        controller = loader.getController();
            ProxyController.changeScene(new Stage(), "addTable.fxml");
    }
    public void setId(Integer id){
        controller.setId(id);
    }
    public void show(){
        stage.show();
    }
}
