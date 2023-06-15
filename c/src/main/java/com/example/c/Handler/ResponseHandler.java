package com.example.c.Handler;

import main.org.example.main.Response;
import main.org.example.main.TypeOfAnswer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ResponseHandler {

    private static ResponseHandler instance;

    private ResponseHandler() {}


    public static ResponseHandler getInstance(){
        if(instance == null) instance = new ResponseHandler();
        return instance;
    }

    public Response receive(ByteBuffer buffer) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
           return  (Response) inputStream.readObject();
//            return Animator.getInstance().animate(response);

        } catch (ClassNotFoundException | InvalidClassException e) {
            return new Response(TypeOfAnswer.ANOTHERVERSION);
        } catch (IOException e) {
            return new Response(TypeOfAnswer.NETPROBLEM);
        }
    }
    public Response receive(TypeOfAnswer information){
        return new Response(information);
    }
}

