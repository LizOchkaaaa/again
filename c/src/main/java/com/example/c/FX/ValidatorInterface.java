package com.example.c.FX;

import main.org.example.main.CommandFactory;
import main.org.example.main.Session;

public interface ValidatorInterface {
    /**
     * Validate Commands without argument
     */
    boolean notObjectArgumentCommands(CommandFactory aCommand);

    /**
     * Validate Commands with object argument
     */
    boolean objectArgumentCommands(CommandFactory aCommand);

    /**
     * Validate Commands with script path argument
     */
    boolean scriptArgumentCommand(CommandFactory aCommand);

    boolean scriptGUIArgumentCommand(CommandFactory aCommand);

    /**
     * Validate Login and Register commands
     */
    boolean sessionCommands(Session aSession);

    boolean validateConnection(String remoteHostAddress, String remoteHostPort);

}
