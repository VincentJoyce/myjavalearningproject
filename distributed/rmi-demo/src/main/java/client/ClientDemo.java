package client;

import cn.lhq.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientDemo {

    public static void main(String[] args) {
        try {
            IHelloService helloService = (IHelloService) Naming.lookup("rmi://127.0.0.1/Hello");
            System.out.println(helloService.sayHello("Lhq"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
