package main.org.example.server.server.commands;

import main.org.example.interfaces.Execute;
import main.org.example.main.Request;
import main.org.example.main.Response;
import main.org.example.main.TypeOfAnswer;
import main.org.example.server.server.Receiver;
public class PrintUniqueFormOfEducationCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public PrintUniqueFormOfEducationCommand(Receiver receiver) {
        super("print_unique_form_of_education", "print the unique values of the" +
                "formOfEducation field of all elements in the collection", 1 , "" , false , true);
        this.receiver = receiver;
    }
    @Override
    public Response execute(Request request) {
        return new Response(receiver.printEnum(), TypeOfAnswer.SUCCESSFUL);
    }
}
