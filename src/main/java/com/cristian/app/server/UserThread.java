package com.cristian.app.server;

import java.io.*;
import java.net.Socket;
import java.util.Set;

public class UserThread extends Thread {
    private final Socket socket;
    private final ChatServer chatServer;
    private PrintWriter writer;
    private String username;

    public UserThread(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);

            printUsers();

            writer.println("username: ");
            this.username = reader.readLine();
            chatServer.addUser(this.username);

            String message;
            do {
                message = "[" + this.username + "] " + reader.readLine();
                chatServer.broadcast(message);
            } while (!message.equals("exit"));

            chatServer.removeUser(this.username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printUsers() {
        Set<String> users = chatServer.getUsers();
        if (users.size() > 0) {
            writer.println("Connected users: ");
            users.forEach(user -> writer.println("\t" + user));
        }
    }

    public void sendMessage(String msg) {
        writer.println(msg);
    }
}
