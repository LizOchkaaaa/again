package com.example.c.FX;

import com.example.c.Worker.ClientWorker;

public class StaticData {
    private static StaticData staticData;
    private String login;
    private ClientWorker connection;

    public static StaticData getData() {
        if (staticData == null) {
            staticData = new StaticData();
        }
        return staticData;
    }

    private StaticData() {}

    public String getLogin() {
        return login;
    }

    public ClientWorker getConnection() {
        return connection;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setConnection(ClientWorker connection) {
        this.connection = connection;
    }
}
