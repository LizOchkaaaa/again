package main.org.example.server.server.commands;

import main.org.example.server.server.Interfaces.Execute;
import main.org.example.server.server.Receiver;
import org.example.main.Request;
import org.example.main.Response;
import org.example.main.TypeOfAnswer;
import org.example.models.StudyGroup;

import java.util.Stack;

public class ShowCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public ShowCommand(Receiver receiver) {
        super("show", "print to standard output all elements of the collection in string representation", 0 , "" , false , true);
        this.receiver = receiver;
    }


    @Override
    public Response execute(Request request) {
        Stack<StudyGroup> studyGroups = receiver.show();
        if (studyGroups == null) return new Response(TypeOfAnswer.EMPTYCOLLECTION);
        else return new Response(studyGroups, TypeOfAnswer.SUCCESSFUL);
    }
}
