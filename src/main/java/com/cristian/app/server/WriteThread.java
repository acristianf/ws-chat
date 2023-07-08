package com.cristian.app.server;

import java.io.*;
import java.net.Socket;

public class WriteThread extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private ChatUser chatUser;

    public WriteThread(Socket socket, ChatUser chatUser) {
        this.socket = socket;
        this.chatUser = chatUser;

        try {
            OutputStream outputStream = socket.getOutputStream();
            this.writer = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        Console console = System.console();

        String text;
        do {
            text = console.readLine();
            writer.println(text);
        } while (!text.equals("exit"));
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
