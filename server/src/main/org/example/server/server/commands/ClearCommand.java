package main.org.example.server.server.commands;

import main.org.example.server.server.Interfaces.Execute;
import main.org.example.server.server.Receiver;
import org.example.main.Request;
import org.example.main.Response;

public class ClearCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public ClearCommand(Receiver receiver) {
        super("clear", "clear the collection", 0 , "" , false, true);
        this.receiver = receiver;
    }


    @Override
    public Response execute(Request request) {
        String username = request.getSession().getName();
        return new Response(receiver.clearCollection(username));
    }
}
