package com.example.c;

import com.example.c.FX.Translation;
import com.example.c.Handler.RequestHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Client extends Application {
    private final int PORT = 43414;
    @Override
    public void start(Stage stage) throws IOException {
        RequestHandler.getInstance().setRemoteHostSocketAddress(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Study group");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        Translation.setLanguage("Русский");
        launch();
    }
}