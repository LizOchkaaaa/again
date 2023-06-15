package main.org.example.server.server.commands;

import main.org.example.interfaces.Execute;
import main.org.example.main.Request;
import main.org.example.main.Response;
import main.org.example.main.TypeOfAnswer;
import main.org.example.models.StudyGroup;
import main.org.example.server.server.Receiver;

import java.util.Stack;
import java.util.stream.Collectors;

public class ReorderCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public ReorderCommand(Receiver receiver) {
        super("reorder", "sort the collection in reverse order", 0 , "" , false , true);
        this.receiver = receiver;
    }


    @Override
    public Response execute(Request request) {
        if (receiver.getMainCollection().isEmpty()){
            return new Response(TypeOfAnswer.EMPTYCOLLECTION);
        }
        return new Response((Stack<StudyGroup>) receiver.getMainCollection().stream().sorted(StudyGroup::compareTo).collect(Collectors.toCollection(Stack::new)), TypeOfAnswer.SUCCESSFUL);
    }
}
