package main.org.example.server.server;


import org.example.main.Request;
import org.example.main.Response;

import java.util.concurrent.Callable;

public class Task implements Callable<Response> {
    private final Invoker invoker;
    private final Request request;

    public Task(Invoker ainvoker, Request arequest) {
        invoker = ainvoker;
        request = arequest;
    }

    @Override
    public Response call() {
        return invoker.execute(request);
    }
}
