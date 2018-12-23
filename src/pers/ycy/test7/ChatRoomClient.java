package pers.ycy.test7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatRoomClient {
    private Socket s;
    private BufferedReader br;
    private PrintWriter pw;

    public ChatRoomClient(String host, int port) throws IOException {
        s = new Socket(host, port);
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw = new PrintWriter(s.getOutputStream());
    }

    public void sendMessage(String str) {
        pw.println(str);
        pw.flush();
    }

    public String reciveMessage() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
