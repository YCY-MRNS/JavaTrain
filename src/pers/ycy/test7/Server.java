package pers.ycy.test7;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("---创建服务器成功，等待接入---");
            Socket socket = serverSocket.accept();
            System.out.println("---接入成功---");

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            String getContent = null;
            while ((getContent = reader.readLine()) != null) {
                System.out.println(getContent);
                if (getContent.equals("start")) {
                    printWriter.print("OK");
                    printWriter.flush();
                }
            }

            socket.shutdownInput();
            socket.shutdownOutput();
            outputStream.close();
            printWriter.close();
            inputStream.close();
            inputStreamReader.close();
            reader.close();
            socket.close();
            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
