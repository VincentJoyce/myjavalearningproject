package cn.lhq;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientDemo {

    public static void main(String[] args) {

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] sendData = "Hello,Lhq".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 8080);
            datagramSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
