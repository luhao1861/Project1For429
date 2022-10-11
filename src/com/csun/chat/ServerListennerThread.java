package com.csun.chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class ServerListennerThread extends Thread {
    private Socket socket;

    private List<String[]> list;

    public ServerListennerThread(Socket socket, List list) {
        this.socket = socket;
        this.list = list;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                if (msg.startsWith("send")) {
                    System.out.println(socket.getRemoteSocketAddress() + " say: " + msg.replaceFirst("send", ""));
                } else if (msg.startsWith("connect")) {
                    String userId;
                    if (list.isEmpty()) {
                        userId = "1";
                    } else {
                        userId = Integer.parseInt(list.get(list.size() - 1)[0]) + 1 + "";
                    }
                    String[] strs = {userId, socket.getRemoteSocketAddress().toString()};
                    list.add(strs);
                } else if (msg.startsWith("exit")) {
                    socket.close();
                    System.out.println(socket.getRemoteSocketAddress() + " exit");
                    break;
                } else if (msg.startsWith("list")) {
                    msg.replaceFirst("list","online list: \n");
                    for (String[] s : list) {
                        msg += " id: " + s[0] + " ip: " + s[1] + " is online \n";
                        System.out.println(msg);
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(socket.getRemoteSocketAddress() + " exit");
        }
    }
}
