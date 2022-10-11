package com.csun.chat;

import java.util.Scanner;

public class Chat {
        String port;
        public void startChat(){
            System.out.println("please type in ./chat Port");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            port = msg.replaceFirst("./chat ","");
            Server server = new Server(Integer.parseInt(port));
            Client client = new Client();
            new Thread(server).start();
            new Thread(client).start();
        }
    public static void main(String[] args) {
            Chat chat = new Chat();
            chat.startChat();
    }
}
