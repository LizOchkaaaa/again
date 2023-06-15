package main.org.example;

import main.org.example.server.server.*;
import main.org.example.server.server.DataBase.DBConnection;
import main.org.example.server.server.DataBase.DBInitializer;
import main.org.example.server.server.DataBase.MainDB;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MainServer {
    public static final Logger logger = Logger.getLogger(MainServer.class.getCanonicalName());
    private static InetSocketAddress address;

    public static void main(String[] args) throws IOException {
        logger.info("Connect with server");
        try (Scanner scanner = new Scanner(System.in)) {
            DatagramChannel server = DatagramChannel.open();
            server.configureBlocking(false);
            int Port = 43414;
            logger.info("Server listening port " + Port);
            address = new InetSocketAddress(Port);
            server.bind(address);
            ConsoleManager consoleManager = new ConsoleManager();
            Thread threads= new Thread(consoleManager);
            threads.start();
            MainDB mainDB = connectToDB();
            if(mainDB == null) return;
            LocalDateBase localDateBase = new LocalDateBase();
            Receiver receiver = new Receiver(mainDB , localDateBase);

            ExecutorService executor = Executors.newCachedThreadPool();
            ExecutorService responseSender = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            Invoker invoker = new Invoker(receiver);

            RequestReceiver requestReceiver = new RequestReceiver(address , server , invoker , executor , responseSender);
            Thread thread = new Thread(requestReceiver);
            thread.start();
        }
    }
    private static MainDB connectToDB() {
        Connection database;
        try {
            database = new DBConnection().connect();
            if (database == null) return null;
            DBInitializer dbInitializer = new DBInitializer(database);
            dbInitializer.initialize();
        } catch (SQLException e) {
            logger.info("Something wrong with data base");
            return null;
        }
        try {
            return new MainDB(database);
        }catch (NoSuchAlgorithmException e){
            logger.info("Hashing algorithm not found!");
            return null;
       }
    }
}
