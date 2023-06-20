package com.example.c.FX;

import com.example.c.Interface.ConsoleInterface;
import com.example.c.Object.CommandManager;
import com.example.c.Object.Consolee;
import com.example.c.controllers.NewLoginController;
import com.example.c.controllers.ProxyController;
import com.example.c.controllers.RegistrationController;
import com.example.c.validatorClient.Validator;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.main.Session;
import org.example.main.TypeOfAnswer;
import org.example.main.TypeOfSession;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewLogin implements Runnable{
    private final ProxyController controller = new ProxyController(NewLoginController.class);
    private ValidatorInterface validator;

    private String login;
    private String password;
    private Locale locale;

    private String mode;

    public NewLogin(String mode, Locale locale) {
        this.locale = locale;
        this.mode = mode;
        validator = Validator.getInstance();
    }

    public boolean registerNew() {
        if (registerEmpty() & registerLong()) {
            login = ((TextField) controller.getField("login")).getText();
            password = ((TextField) controller.getField("password")).getText();

            Session session = new Session(login, password, TypeOfSession.Register);
            CommandManager commandManager = new CommandManager(validator, controller);
            if (commandManager.transferCommand(session).getStatus() == TypeOfAnswer.SUCCESSFUL){
                return true;
            }
            else {
                return false;
            }

        }
        return false;
    }

    @Override
    public void run() {

    }
    private boolean registerEmpty() {
        ProxyController controller = new ProxyController(NewLoginController.class);

        boolean result = true;
        String bundle = "properties.Register";


        TextField login = controller.getField("login");
        if (login.getText().length() == 0) {
            Label label = controller.getField("loginWarn");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginEmpty"));
            result = false;
        }

        TextField password = controller.getField("password");
        if (password.getText().length() == 0) {
            Label label = controller.getField("passwordWarn");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordEmpty"));
            result = false;
        }

        return result;

    }
    public boolean registerLong() {
        ProxyController controller = new ProxyController(NewLoginController.class);
        boolean result = true;
        String bundle = "properties.Register";

        TextField login = controller.getField("login");
        if (login.getText().length() > 32) {
            Label label = controller.getField("loginWarn");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginLong"));
            result = false;
        }

        TextField password = controller.getField("password");
        if (password.getText().length() > 32) {
            Label label = controller.getField("passwordWarn");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordLong"));
            result = false;
        }

        return result;
    }

}
//         validation = new Validation();
//
//        if (validation.registerEmpty(locale) & validation.registerLong(locale)) {
//            Object[] fields = controller.getFields("register", "enter", "languages");
//            Arrays.stream(fields).forEach(r -> ((Node) r).setDisable(true));
//
//            host = ((TextField) controller.getField("host")).getText();
//            port = ((TextField) controller.getField("port")).getText();
//            initialize();
//            new Thread(this).start();
//
//            return true;
//        }
//        return false;


//    @FXML
//    public void initialize() {
//        ((Label) controller.getField("hostInput")).setText(host);
//        ((Label) controller.getField("portInput")).setText(port);
//
//        AnchorPane anchorPane = controller.getField("mainAnchor");
//        AnchorPane connectionAnchor = controller.getField("connectionAnchor");
//
//        String[] fields = {"connectionText", "hostText", "portText", "cancelButton"};
//        String[] keys = {"connectionText", "host_", "port_", "cancelButton"};
////        new NodeManager().setText(RegistrationController.class, bundle, locale, fields, keys);
//
//        rectangle.setStroke(Color.WHITE);
//        rectangle.setLayoutX(175);
//        rectangle.setLayoutY(193);
//        try {
//            anchorPane.getChildren().add(13, rectangle);
//        } catch (IllegalArgumentException ignored) {
//        } //если объект добавлен, то просто проигнорировать
//    }
//
//    @Override
//    public void run() {
//        System.out.println("TTTT");
//    }
//
//    public void cancel() {
//        AnchorPane connectionAnchor = controller.getField("connectionAnchor");
//
//        Object[] fields = controller.getFields("register", "enter", "languages");
//        Arrays.stream(fields).forEach(r -> ((Node) r).setDisable(false));
//    }
//
//}

//        Animations animations = new Animations();
//        animations.scaleTransition(Duration.millis(250), rectangle, 0,0,302,217);
//        connectionAnchor.setVisible(true);
//        animations.scaleTransition(Duration.millis(250), connectionAnchor, 0,0,1,1);
//    }
//
//    @Override
//    public void run() {
//        String login = ((TextField) controller.getField("login")).getText();
//        String password = hash(((TextField) controller.getField("password")).getText());
//        Connection connection = new Connection(host, Integer.parseInt(port));
//
//        if (connection.connect()) {
//            connection.sendToServer("user_access", mode, login, password);
//            try {
//                String key = connection.getFromServer();
//
//                if (!key.equals("access")) {
//                    Platform.runLater(() -> new NodeManager().setText(RegistrationController.class,
//                            bundle, locale, new String[]{"loginLabel"}, new String[]{key}));
//
//                    connection.close();
//                } else {
//                    Stage stage = (Stage) ((Node) controller.getField("enter")).getScene().getWindow();
//                    Platform.runLater(() -> ProxyController.changeScene(stage, "table.fxml"));
//                    new DragonTable().getAndAdd(connection.getSocket());
//                    StaticData.getData().setLogin(login);
//                    StaticData.getData().setConnection(connection);
//                    new Thread(connection).start();
//                }
//            } catch (IOException e) {e.printStackTrace();}
//            cancel();
//        }
//    }

//    private String hash(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-224");
//            byte[] bytes = md.digest(password.getBytes());
//            BigInteger integer = new BigInteger(1, bytes);
//            return integer.toString(16);
//        } catch (NoSuchAlgorithmException e) {e.printStackTrace();}
//        return null;
//    }
