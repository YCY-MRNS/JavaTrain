package pers.ycy.test7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author:袁阊越
 * @Package：pers.ycy.test7
 * @Date: 2018/12/21 20:12
 * @Description:
 **/

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            datagramSocket.receive(packet);
            System.out.println("---服务端已经开始---");

            String string = new String(data, 0, packet.getLength());
            System.out.println("服务端接收到客户端的内容：" + string);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
