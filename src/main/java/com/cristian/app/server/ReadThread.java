package com.cristian.app.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatUser chatUser;

    public ReadThread(Socket socket, ChatUser chatUser) {
        this.socket = socket;
        this.chatUser = chatUser;

        try {
            InputStream input = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String res = this.reader.readLine();
                System.out.println(res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
