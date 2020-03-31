package cn.lhq;

import java.io.*;
import java.net.Socket;

public class ClientDemo1 {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = sin.readLine();
            while (!line.equals("bye")) {

                os.println(line);//输出数据
                os.flush();
                System.out.println("Client:" + line);
                System.out.println("Server:" + is.readLine());//拿到服务端的消息
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
