package pers.ycy.test7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class ChatRoomServer {
    private ServerSocket ss;
    private HashSet<Socket> allSockets;

    public ChatRoomServer() {
        try {
            ss = new ServerSocket(4569);
        } catch (IOException e) {
            e.printStackTrace();
        }
        allSockets = new HashSet<Socket>();
    }

    public void startService() throws IOException {
        while (true) {
            Socket s = ss.accept();
            System.out.println("用户已进入聊天室");
            allSockets.add(s);
            new ServerThread(s).start();
        }
    }

    private class ServerThread extends Thread {
        Socket s;

        public ServerThread(Socket s) {
            this.s = s;
        }

        public void run() {
            BufferedReader br = null;

            try {
                br = new BufferedReader(new InputStreamReader(
                        s.getInputStream()));
                while (true) {
                    String str = br.readLine();
                    if (str.indexOf("%EXIT%") == 0) {
                        allSockets.remove(s);
                        sendMessageTOAllClient(str.split(":")[1]
                                + " 用户已退出聊天室");
                        s.close();
                        return;

                    }
                    sendMessageTOAllClient(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessageTOAllClient(String message) throws IOException {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            System.out.println(message + "\t[" + df.format(date) + "]");
            for (Socket s : allSockets) {
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                pw.println(message + "\t[" + df.format(date) + "]");
                pw.flush();
            }

        }
    }

    public static void main(String[] args) {
        try {
            new ChatRoomServer().startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
