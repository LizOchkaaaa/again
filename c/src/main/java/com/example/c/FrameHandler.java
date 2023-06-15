package com.example.c;

import com.example.c.Handler.RequestHandler;
import com.example.c.controllers.RegistrationController;
import com.example.c.validatorClient.Validator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.org.example.main.CommandFactory;
import main.org.example.main.Session;
import main.org.example.main.TypeOfAnswer;
import main.org.example.main.TypeOfSession;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Locale;
public class FrameHandler {
    private final CommandReader commandReader;
    private final RegistrationController registerController;

    public FrameHandler(RegistrationController registerController) {
        this.registerController = registerController;
        commandReader = CommandReader.getInstance();
    }

    public void connect(String remoteHostAddress, String remoteHostPort) {
        if (Validator.getInstance().validateConnection(remoteHostAddress, remoteHostPort)) {
            try {
                RequestHandler.getInstance().setRemoteHostSocketAddress(
                        new InetSocketAddress(InetAddress.getByName(remoteHostAddress), Integer.parseInt(remoteHostPort)));
                setAuth();
            } catch (UnknownHostException unknownHostException) {
//                connectController.setWarn(TypeOfAnswer.SERVERNOTAVAILABLE);
            }
        } else {
            TypeOfAnswer notvalidate = TypeOfAnswer.NOTVALIDATE;
        }
    }

    public void setAuth() {
        Stage stage = new Stage();
        stage.setTitle("Login");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("path/to/login.fxml"));
        try {
            StackPane loginPane = loader.load();
            stage.setScene(new Scene(loginPane));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setRegister() {
        Stage stage = new Stage();
        stage.setTitle("Register");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("path/to/register.fxml"));
        loader.setController(registerController);
        try {
            StackPane registerPane = loader.load();
            stage.setScene(new Scene(registerPane));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(String anUsername, char[] aPassword) {
        Session session = new Session(anUsername, new String(aPassword), TypeOfSession.Login);
        TypeOfAnswer status = commandReader.execute(session).getStatus();
    }

    public void register(String anUsername, char[] aPassword) {
        Session session = new Session(anUsername, new String(aPassword), TypeOfSession.Register);
        TypeOfAnswer status = commandReader.execute(session).getStatus();
    }

    public void spawnAddDetailsModel(CommandFactory aCommand) {

    }
}
