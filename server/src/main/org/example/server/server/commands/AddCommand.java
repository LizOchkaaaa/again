package main.org.example.server.server.commands;

import main.org.example.main.Request;
import main.org.example.main.Response;
import main.org.example.models.StudyGroup;
import main.org.example.server.server.Interfaces.Execute;
import main.org.example.server.server.Receiver;

public class AddCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public AddCommand(Receiver receiver) {
        super("add", "add a new element to the collection" , 0 , "{element}" , true, true);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        StudyGroup studyGroup = request.getCommand().getStudyGroup();
        return new Response(receiver.add(studyGroup));
    }

}
