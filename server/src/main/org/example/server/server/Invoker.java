package main.org.example.server.server;
import main.org.example.server.server.commands.*;
import org.example.main.Request;
import org.example.main.Response;
import org.example.main.TypeOfAnswer;
import org.example.main.TypeOfCommand;

import java.util.HashMap;

import static main.org.example.server.server.Deliver.logger;

public class Invoker {
    private static Receiver receiver;

    private static HashMap<TypeOfCommand, AbstractCommand> commandsMap;

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

        var sumOfStudentsCountCommand = new SumOfStudentsCountCommand(receiver);
        var printCommand = new PrintUniqueFormOfEducationCommand(receiver);
        var printFieldCommand = new PrintFieldAscendingStudentsCountCommand(receiver);
        var register = new RegisterUser(receiver);
        var login = new LoginUserCommand(receiver);

        commandsMap.put(TypeOfCommand.help, helpCommand);
        commandsMap.put(TypeOfCommand.info, infoCommand);
        commandsMap.put(TypeOfCommand.clear, clearCommand);
        commandsMap.put(TypeOfCommand.show, showCommand);
        commandsMap.put(TypeOfCommand.add, addCommand);
        commandsMap.put(TypeOfCommand.update, updateCommand);
        commandsMap.put(TypeOfCommand.remove_by_id, removeCommand);
        commandsMap.put(TypeOfCommand.execute_script, executeCommand);
        commandsMap.put(TypeOfCommand.add_if_max, addMaxCommand);
        commandsMap.put(TypeOfCommand.remove_greater, remove1Command);
        commandsMap.put(TypeOfCommand.sum_Of_Students_Count, sumOfStudentsCountCommand);
        commandsMap.put(TypeOfCommand.print_Unique_Form_Of_Education, printCommand);
        commandsMap.put(TypeOfCommand.print_Field_Ascending_Students_Count, printFieldCommand);
        commandsMap.put(TypeOfCommand.register , register);
        commandsMap.put(TypeOfCommand.login , login);
        commandsMap.put(TypeOfCommand.exit , exitCommand);
    }


    public Response execute(Request aRequest) {
        TypeOfCommand aCommand = TypeOfCommand.valueOf(aRequest.getCommand().getCommand());
        String username = aRequest.getSession().getName();
        String password = aRequest.getSession().getPassword();
        logger.info(String.valueOf(commandsMap.get(aCommand).getAuthorizationStatus()));
        logger.info(String.valueOf(aCommand == TypeOfCommand.register || receiver.loginUser(username, password)));
        return (!commandsMap.get(aCommand).getAuthorizationStatus())
                ? commandsMap.get(aCommand).execute(aRequest)
                : (aCommand == TypeOfCommand.register || receiver.loginUser(username, password))
                ? commandsMap.get(aCommand).execute(aRequest)
                : new Response(TypeOfAnswer.NOTMATCH);
    }

    public static HashMap<TypeOfCommand, AbstractCommand> getCommandsMap(){
        return commandsMap;
    }
}
