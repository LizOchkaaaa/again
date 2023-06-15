package main.org.example.server.server.commands;

import main.org.example.interfaces.Execute;
import main.org.example.main.Request;
import main.org.example.main.Response;
import main.org.example.main.TypeOfAnswer;
import main.org.example.server.server.Receiver;

public class SumOfStudentsCountCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public SumOfStudentsCountCommand(Receiver receiver) {
        super("sum_of_students_count", "display the sum of the values of the studentsCount" +
                " field for all elements of the collection",  0 , "" , false , true);
        this.receiver = receiver;
    }


    @Override
    public Response execute(Request request) {
        return new Response(receiver.sumOfStudentsCount() , TypeOfAnswer.SUCCESSFUL);
    }
}
