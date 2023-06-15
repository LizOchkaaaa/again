package com.example.c;

import com.example.c.Handler.RequestHandler;
import com.example.c.controllers.ValidatorInterface;
import javafx.stage.FileChooser;
import main.org.example.main.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class CommandManager {
    private final ValidatorInterface validator;
    private final RequestHandlerInterface requestHandler;
    private final ConsoleInterface console;
    private final Set<String> usedScripts;
    private final ProxyController controller;

    public CommandManager(ValidatorInterface validator, ProxyController controller) {
        this.validator = validator;
        this.controller = controller;
        requestHandler = (RequestHandlerInterface) RequestHandler.getInstance();
        console = (ConsoleInterface) Consolee.getInstance();
        usedScripts = new HashSet<>();
    }

    public void transferCommand(CommandFactory aCommand) {
        if (validator.notObjectArgumentCommands(aCommand)) {
            if (aCommand.getCommand().equals(TypeOfCommand.Show)) {
//                mainModelAnimator.animateShow(requestHandler.send(aCommand));
//                visualModelAnimator.animateShow(requestHandler.send(aCommand));
            } else {
//                logger.info("Обрабатываем команду:" + aCommand);
//                mainModelAnimator.animate(requestHandler.send(aCommand));
            }
        } else if (validator.objectArgumentCommands(aCommand)) {
            if (console.getExeStatus()) aCommand.addStudyGroup(new StudyGroupFactory().createScriptStudyGroup());
            else {
//                frameHandler.spawnAddDetailsModel(aCommand);
            }

            if (aCommand.getStudyGroup() != null) {
//                mainModelAnimator.animate(requestHandler.send(aCommand));
//            } else mainModelAnimator.animate(new Response(TypeOfAnswer.NOTVALIDATE));
            } else if (validator.scriptGUIArgumentCommand(aCommand)) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Открыть файл");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
//                executeScript(file);
//            }
//        } else if (validator.scriptArgumentCommand(aCommand)) {
//            executeScript(new File(String.valueOf(aCommand.getArg())));
//        } else mainModelAnimator.animate(new Response(TypeOfAnswer.NOTVALIDATE));
//    }
                }
            }
        }
    }
    public Response transferCommand(Session aSession) {
        if (validator.sessionCommands(aSession)) {
            if ((aSession.getTypeOfSession() == TypeOfSession.Register)) {
                return requestHandler.register(aSession);
            } else {
                return requestHandler.login(aSession);
            }
        }
        return new Response(TypeOfAnswer.NOTVALIDATE);
    }

    private void executeScript(File script) {

        if (usedScripts.add(script.getAbsolutePath())) {
            if (usedScripts.size() == 1) console.setExeStatus(true);

//                ScriptReader scriptReader = new ScriptReader(script);
            //                    scriptReader.read();

//                    mainModelAnimator.animate(new Response(TypeOfAnswer.SUCCESSFUL));
            usedScripts.remove(script.getAbsolutePath());
            if (usedScripts.size() == 0) console.setExeStatus(false);
        }
    }
}
