package socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3888);

        System.out.println("Server将一直等待连接的到来");
        Socket socket = serverSocket.accept();
        // 建立好连接后, 从socket中获取输入流, 并建立缓冲区进行读取.
        InputStream inputStream = socket.getInputStream();
        // 用户态缓冲区
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            // 统一编码格式, 发送方和接收方都要统一成UTF8
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("get message from client: " + sb);
        inputStream.close();
        serverSocket.close();
    }
}
