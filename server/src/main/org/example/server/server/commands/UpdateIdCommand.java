package main.org.example.server.server.commands;

import main.org.example.interfaces.Execute;
import main.org.example.main.Request;
import main.org.example.main.Response;
import main.org.example.models.StudyGroup;
import main.org.example.server.server.Receiver;

public class UpdateIdCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;;
    public UpdateIdCommand(Receiver receiver) {
        super("update", "update the value of the collection element whose" +
                " id is equal to the given one", 1 , "id {element}" , true , true);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        StudyGroup upgradedGroup = request.getCommand().getStudyGroup();
        int id = Integer.parseInt(request.getCommand().getArg());
        return new Response(receiver.updateId(upgradedGroup, id));
    }

}

