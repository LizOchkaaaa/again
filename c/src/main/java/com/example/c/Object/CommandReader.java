package com.example.c.Object;

import org.example.main.CommandFactory;
import org.example.main.Response;
import org.example.main.Session;
import org.example.main.TypeOfCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandReader {
    private CommandManager commandManager;
    private static CommandReader instance;

    public static CommandReader getInstance() {
        if (instance == null) instance = new CommandReader();
        return instance;
    }

    private CommandReader() {
    }
    public void setCommandManager(CommandManager aCommandManager) {
        commandManager = aCommandManager;
    }

    /**
     * Method to register/auth user
     */

    public Response execute(Session session) {
        return commandManager.transferCommand(session);
    }

    /**
     * Method to execute command
     */
    public void execute(CommandFactory command) {
        commandManager.transferCommand(command);
    }

    public CommandFactory readCommand(String anInputString) {

        if (anInputString == null) return null;

        String command;
        String arg;
        Pattern commandName = Pattern.compile("^\\w+\\s+");
        Pattern argName = Pattern.compile("^.+");
        Matcher matcher = commandName.matcher(anInputString + " ");

        if (matcher.find()) {
            command = matcher.group().trim();
        } else {
            return null;
        }

        matcher = argName.matcher(anInputString.substring(command.length()));

        if (matcher.find()) {
            arg = matcher.group().trim();
            if (arg.equals("")) arg = null;
        } else arg = null;

        return new CommandFactory(TypeOfCommand.getEnum(command), arg);
    }
}
