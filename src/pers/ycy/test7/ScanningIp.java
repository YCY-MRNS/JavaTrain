package pers.ycy.test7;

import java.io.IOException;
import java.net.InetAddress;

public class ScanningIp {

    public static void main(String[] args) {
        String IP = null;
        for (int i = 10; i <= 50; i++) {
            IP = "192.168.11." + i;
            try {
                InetAddress host = InetAddress.getByName(IP);
                if (host.isReachable(1000)) {
                    String hostName = host.getHostName();
                    System.out.println("IP地址为" + IP + "的知己名称为:" + hostName);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("搜索完毕");
    }
}
