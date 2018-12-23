package pers.ycy.test7;

import java.io.IOException;
import java.net.*;

/**
 * @Author:袁阊越
 * @Package：pers.ycy.test7
 * @Date: 2018/12/21 20:13
 * @Description:
 **/

public class UDPClient {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8888;
            byte[] data = "1000".getBytes();

            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(new DatagramPacket(data, data.length, address, port));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
