package pers.ycy.test7;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author:袁阊越
 * @Package：pers.ycy.test7
 * @Date: 2018/12/21 12:08
 * @Description:
 **/

public class ClientConnection {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print("我是" + InetAddress.getByName("localhost").getHostAddress());
            printWriter.flush();
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String getContent = null;
            while ((getContent = reader.readLine()) != null) {
                System.out.println(getContent);
            }

            socket.shutdownInput();
            inputStream.close();
            inputStreamReader.close();
            reader.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}