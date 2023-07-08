package com.cristian.app.server;

import java.io.IOException;
import java.net.Socket;

public class ChatUser {

    private final String hostname;
    private final Integer port;

    public ChatUser(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket clientSocket = new Socket(hostname, port);
            System.out.println("Connected to chat server!");

            new ReadThread(clientSocket, this).start();
            new WriteThread(clientSocket, this).start();

        } catch (IOException | RuntimeException e) {
            System.err.println("Couldn't connect to " + hostname + ":" + port + ".");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}