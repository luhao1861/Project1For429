package com.csun.chat;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    //    InheritableThreadLocal<List<String[]>> itl = new InheritableThreadLocal<>();
    ArrayList<String[]> list = new ArrayList<>();
    private String ip;
    private int port;
    public Server(int port){
        this.port = port;
    }
    public void startServer() {
        try {
//          itl.set(list);
            System.out.println("----------------Server Start-----------------------ip: "+InetAddress.getLocalHost().getHostAddress()+", port: " + port);
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + " online");
                new ServerListennerThread(socket, list).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        startServer();
    }
}
