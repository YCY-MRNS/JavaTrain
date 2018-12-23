package per.ycy.classroom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

        try {
            DatagramSocket socket = new DatagramSocket(9527);
            socket.receive(datagramPacket);
            int length = datagramPacket.getLength();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}