package main.org.example.server.server;

import main.org.example.main.Request;
import main.org.example.main.Response;
import main.org.example.main.TypeOfAnswer;
import main.org.example.main.TypeOfCommand;
import main.org.example.server.server.commands.*;

import java.util.HashMap;

import static main.org.example.server.server.Deliver.logger;

public class Invoker {
    private static Receiver receiver;

    private static HashMap<String, AbstractCommand> commandsMap;

    public Receiver getReceiver() {
        return receiver;
    }

    public Invoker(Receiver receiver) {
        this.receiver = receiver;

        commandsMap = new HashMap<>();
        var helpCommand = new HelpCommand(receiver, commandsMap.values());
        var infoCommand = new InfoCommand(receiver);
        var exitCommand = new Exit(receiver);
        var clearCommand = new ClearCommand(receiver);
        var showCommand = new ShowCommand(receiver);
        var addCommand = new AddCommand(receiver);
        var updateCommand = new UpdateIdCommand(receiver);
        var removeCommand = new RemoveByIdCommand(receiver);
        var executeCommand = new ExecuteScriptCommand();
        var addMaxCommand = new AddIfMaxCommand(receiver);
        var remove1Command = new RemoveGreaterCommand(receiver);
        var reorderCommand = new ReorderCommand(receiver);
        var sumOfStudentsCountCommand = new SumOfStudentsCountCommand(receiver);
        var printCommand = new PrintUniqueFormOfEducationCommand(receiver);
        var printFieldCommand = new PrintFieldAscendingStudentsCountCommand(receiver);
        var register = new RegisterUser(receiver);
        var login = new LoginUserCommand(receiver);

        commandsMap.put("help", helpCommand);
        commandsMap.put("info", infoCommand);
        commandsMap.put("clear", clearCommand);
        commandsMap.put("show", showCommand);
        commandsMap.put("add", addCommand);
        commandsMap.put("update", updateCommand);
        commandsMap.put("remove_by_id", removeCommand);
        commandsMap.put("execute_script", executeCommand);
        commandsMap.put("add_if_max", addMaxCommand);
        commandsMap.put("remove_greater", remove1Command);
        commandsMap.put("reorder", reorderCommand);
        commandsMap.put("sum_of_students_count", sumOfStudentsCountCommand);
        commandsMap.put("print_unique_form_of_education", printCommand);
        commandsMap.put("print_field_ascending_students_count", printFieldCommand);
        commandsMap.put("register" , register);
        commandsMap.put("login" , login);
        commandsMap.put("exit" , exitCommand);
    }


    public  Response execute(Request aRequest) {
        TypeOfCommand aCommand = TypeOfCommand.valueOf(aRequest.getCommand().getCommand());
        String username = aRequest.getSession().getName();
        String password = aRequest.getSession().getPassword();
        logger.info(String.valueOf(commandsMap.get(aCommand).getAuthorizationStatus()));
        logger.info(String.valueOf(aCommand == TypeOfCommand.Register || receiver.loginUser(username, password)));
        return (!commandsMap.get(aCommand).getAuthorizationStatus())
                ? commandsMap.get(aCommand).execute(aRequest)
                : (aCommand == TypeOfCommand.Register || receiver.loginUser(username, password))
                ? commandsMap.get(aCommand).execute(aRequest)
                : new Response(TypeOfAnswer.NOTMATCH);
    }

    public static HashMap<String, AbstractCommand> getCommandsMap(){
        return commandsMap;
    }
}
