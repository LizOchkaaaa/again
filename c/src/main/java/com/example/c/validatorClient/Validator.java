package com.example.c.validatorClient;

import com.example.c.Object.AvailableCommands;
import com.example.c.FX.ValidatorInterface;
import main.org.example.main.CommandFactory;
import main.org.example.main.Session;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class Validator implements ValidatorInterface {
    private static AvailableCommands availableCommands;
    private static Validator instance;

    private Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            availableCommands = new AvailableCommands();
            instance = new Validator();
        }
        return instance;
    }
    public boolean notObjectArgumentCommands(CommandFactory aCommand) {
        return validateNoArgumentCommand(aCommand) ||
                validateNumArgumentCommands(aCommand) ||
                validateStringArgumentCommands(aCommand);
    }

    public boolean objectArgumentCommands(CommandFactory aCommand) {
        return validateObjectArgumentCommands(aCommand) ||
                validateObjAndNumArgumentCommand(aCommand);
    }
    public boolean scriptArgumentCommand(CommandFactory aCommand) {
        return availableCommands.scriptArgumentCommand.equals(aCommand.getCommand()) &&
                aCommand.getArg() != null;
    }

    public boolean scriptGUIArgumentCommand(CommandFactory aCommand) {
        return availableCommands.scriptArgumentCommand.equals(aCommand.getCommand()) &&
                aCommand.getArg() == null;
    }
    public boolean sessionCommands(Session aSession) {
        String username = aSession.getName();
        String password = aSession.getPassword();
        return validateLogin(username) && validatePassword(password);
    }
    public boolean validateConnection(String remoteHostAddress, String remoteHostPort) {
        return validateHostAddress(remoteHostAddress) && validateHostPort(remoteHostPort);
    }

    private boolean validateHostAddress(String remoteHostAddress) {
        Pattern hostAddress = Pattern.compile("^\\s*([\\w.]+)\\s*");

        if (hostAddress.matcher(remoteHostAddress).find()) {
            try {
                InetAddress.getByName(remoteHostAddress);
                return true;
            } catch (UnknownHostException e) {
                return false;
            }
        } else return false;
    }

    private boolean validateHostPort(String port) {
        Pattern remoteHostPortPattern = Pattern.compile("^\\s*\\b(\\d{1,5})\\b\\s*");

        return remoteHostPortPattern.matcher(port).find() && (Integer.parseInt(port.trim()) < 65536
                || Integer.parseInt(port.trim()) > 0);
    }

    private boolean validateLogin(String username) {
        Pattern usernamePattern = Pattern.compile("^\\s*\\b(\\w+)\\b\\s*");
        return username != null && usernamePattern.matcher(username).find();
    }

    private boolean validatePassword(String password) {
        Pattern passwordPattern = Pattern.compile("^\\s*([\\d\\w]*)\\s*");
        return password == null || passwordPattern.matcher(password).find();
    }

    private boolean validateNoArgumentCommand(CommandFactory aCommand) {
        return availableCommands.noArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.getArg() == null;
    }

    private boolean validateNumArgumentCommands(CommandFactory aCommand) {
        return availableCommands.numArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.isArgInt() && Integer.parseInt(aCommand.getArg()) > 0;
    }

    private boolean validateStringArgumentCommands(CommandFactory aCommand) {
        return availableCommands.stringArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.getArg() != null;
    }

    private boolean validateObjectArgumentCommands(CommandFactory aCommand) {
        return availableCommands.objectArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.getArg() == null;
    }

    private boolean validateObjAndNumArgumentCommand(CommandFactory aCommand) {
        return (availableCommands.objAndNumArgumentCommand == aCommand.getCommand()) &&
                aCommand.isArgInt() && Integer.parseInt(aCommand.getArg()) > 0;
    }
}