package cn.lhq;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServerDemo {

    public static void main(String[] args) {

        try {
            DatagramSocket datagramSocket = new DatagramSocket(8080);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);

            System.out.println(new String(receiveData, 0, receivePacket.getLength()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
