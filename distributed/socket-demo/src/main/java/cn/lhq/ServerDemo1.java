package cn.lhq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Client:" + is.readLine());
            String line = sin.readLine();
            while (!line.equals("bye")) {
                os.println(line);//输出数据
                os.flush();
                System.out.println("Server:" + line);
                System.out.println("Client:" + is.readLine());//拿到客户端的消息
                line = sin.readLine();
            }
            os.close();
            is.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
