package com.cristian.app;

import com.cristian.app.server.ChatServer;
import com.cristian.app.server.ChatUser;

public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("USAGE: app [server|client]");
            System.exit(1);
        }

        if (args[0].equals("server")) {
            int port = 8082;
            if (args.length == 2) {
                port = Integer.parseInt(args[1]);
            }
            ChatServer chatServer = new ChatServer();
            chatServer.start(port);
        } else if (args[0].equals("client")) {
            if (args.length < 3) {
                System.out.println("USAGE: app client HOSTNAME PORT");
                System.exit(1);
            }
            String hostname = args[1];
            Integer port = Integer.parseInt(args[2]);
            ChatUser chatUser = new ChatUser(hostname, port);
            chatUser.execute();
        }

    }
}
