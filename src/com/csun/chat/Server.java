package com.csun.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {
    //    InheritableThreadLocal<List<String[]>> itl = new InheritableThreadLocal<>();
    ArrayList<String[]> list = new ArrayList<>();

    public void startServer() {
        try {
//          itl.set(list);
            System.out.println("----------------Server Start-----------------------");
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + " online");
                new ServerListennerThread(socket, list).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}
