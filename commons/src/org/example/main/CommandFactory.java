package org.example.main;

import org.example.models.StudyGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandFactory implements Serializable {
    private final String commandName;
    private final String argName;
    private StudyGroup studyGroup;

    public CommandFactory(TypeOfCommand aCommand, String aArgs) {
        commandName = String.valueOf(aCommand);
        argName = aArgs;
        studyGroup = null;
    }

    public CommandFactory(TypeOfCommand anEnum, ArrayList<String> arg) {
        commandName = String.valueOf(anEnum);
        argName = String.valueOf(arg);
    }


    public CommandFactory addStudyGroup(StudyGroup aStudyGroup) {
        studyGroup = aStudyGroup;
        return this;
    }

    public String getCommand() {
        return commandName;
    }

    public String getArg() {
        return argName;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }


    @Override
    public String toString() {
        return commandName + " "
                + (argName != null ? argName : "")
                + (studyGroup != null ? studyGroup : "");
    }

    public boolean isArgInt() {
        try {
            if (argName != null) {
                Integer.parseInt(String.valueOf(argName));
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
