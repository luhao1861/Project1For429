package com.csun.chat;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private String ip;
    private int port = 9999;
    public void sendMsg() {
        System.out.println("---------------Client Start---------------------");
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                if (!msg.isEmpty()) {
                    if (msg.startsWith("help")) {
                        System.out.println("1. help Display information about the available user interface options or command manual.");
                        System.out.println("2. myip Display the IP address of this process.");
                        System.out.println("3. myport Display the port on which this process is listening for incoming connections.");
                        System.out.println("4. connect  <destination>  <port  no>  :  This  command establishes  a  new TCP  connection to  the  specified \n" +
                                "<destination> at the specified < port no>. The <destination> is the IP address of the computer. Any attempt \n" +
                                "to  connect  to  an  invalid  IP  should  be  rejected and  suitable error message  should  be  displayed.  Success  or \n" +
                                "failure  in  connections  between  two  peers  should  be  indicated  by  both  the  peers  using  suitable  messages. \n" +
                                "Self-connections and duplicate connections should be flagged with suitable error messages.");
                        System.out.println("5. list Display a numbered list of all the connections this process is part of. This numbered list will include \n" +
                                "connections  initiated  by  this  process  and  connections  initiated  by  other  processes.  The  output  should \n" +
                                "display the IP address and the listening port of all the peers the process is connected to. ");
                        System.out.println("6.  terminate  <connection  id.>  This  command  will  terminate  the  connection  listed  under  the  specified \n" +
                                "number  when  LIST  is  used  to  display  all  connections.");
                        System.out.println("7. send  <connection id.>  <message> This will \n" +
                                "send the message to the host on the connection that is designated by the number 3 when command “list” is \n" +
                                "used. ");
                        System.out.println("8. exit Close all connections and terminate this process. The other peers should also update their connection \n" +
                                "list by removing the peer that exits.");
                    } else if (msg.startsWith("send")) {
                        Socket socket = new Socket(ip, port);
                        OutputStream os = socket.getOutputStream();
                        PrintStream ps = new PrintStream(os);
                        System.out.println("say: " + msg.replaceFirst("send",""));
                        ps.println(msg);
                        ps.flush();
                    }else if(msg.startsWith("myip")){
                        System.out.println("My ip is: " + InetAddress.getLocalHost().getHostAddress());
                    }else if(msg.startsWith("myport ")){
                        System.out.println("My server listening  port  is: 9999");
                    }else if(msg.startsWith("connect")){
                        String[] strs = msg.split(" ");
                        this.ip = strs[1];
                        this.port = Integer.parseInt(strs[2]);
                        Socket socket = new Socket(ip, port);
                        OutputStream os = socket.getOutputStream();
                        PrintStream ps = new PrintStream(os);
                        System.out.println("you are online");
                        ps.println(msg);
                        ps.flush();
                    }else if(msg.startsWith("exit")){
                        Socket socket = new Socket(ip, port);
                        OutputStream os = socket.getOutputStream();
                        PrintStream ps = new PrintStream(os);
                        System.out.println("you are exit");
                        ps.println(msg);
                        ps.flush();
                        System.out.println(InetAddress.getLocalHost().getHostAddress() +" exit");
                    }else if(msg.startsWith("list")){
                        Socket socket = new Socket(ip, port);
                        OutputStream os = socket.getOutputStream();
                        PrintStream ps = new PrintStream(os);
                        ps.println(msg);
                        ps.flush();
                    }else if(msg.startsWith("terminate")){

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.sendMsg();
    }

    @Override
    public void run() {
        sendMsg();
    }
}
