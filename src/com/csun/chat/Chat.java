package com.csun.chat;

public class Chat {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();
        new Thread(server).start();
        new Thread(client).start();
    }
}
