package pers.ycy.test7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {

    public static void main(String[] args) {
        try {
            int count = 0;
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("---服务器启动成功，等待客服进入---");

            while (true) {

                Socket socket = serverSocket.accept();
                SocketThread socketThread = new SocketThread(socket);
                socketThread.start();
                System.out.println("---有新的客户端连接成功---");
                count++;
                System.out.println(count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
