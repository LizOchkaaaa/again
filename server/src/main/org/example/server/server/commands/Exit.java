package main.org.example.server.server.commands;
import main.org.example.server.server.Receiver;
import org.example.main.Request;
import org.example.main.Response;

import java.io.Serializable;

public class Exit extends AbstractCommand implements Serializable {
    public Exit(Receiver receiver) {
        super("exit", "to exit the program", 0, "", false, true);
    }
    @Override
    public Response execute(Request aRequest) {
        return null;
    }
}
