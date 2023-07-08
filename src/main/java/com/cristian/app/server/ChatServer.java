package com.cristian.app.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static final Set<UserThread> usersThread = new HashSet<>();
    private static final Set<String> users = new HashSet<>();

    public void start(Integer port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started at " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected " + clientSocket);

                UserThread newUser = new UserThread(clientSocket, this);
                usersThread.add(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String msg) {
        usersThread.forEach(user -> user.sendMessage(msg));
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public void addUser(String username) {
        users.add(username);
    }

    public Set<String> getUsers() {
        return users;
    }
}
