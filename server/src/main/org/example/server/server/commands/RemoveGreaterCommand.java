package main.org.example.server.server.commands;

import main.org.example.server.server.Interfaces.Execute;
import main.org.example.server.server.Receiver;
import org.example.main.Request;
import org.example.main.Response;
import org.example.models.StudyGroup;

public class RemoveGreaterCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public RemoveGreaterCommand(Receiver receiver) {
        super("remove_greater", "remove from the collection all elements greater than the given", 0 , "{element}", true , true);
        this.receiver = receiver;

    }

    @Override
    public Response execute(Request request){
        StudyGroup studyGroup = request.getCommand().getStudyGroup();
        return new Response(receiver.removeGreater(studyGroup));
    }
}
