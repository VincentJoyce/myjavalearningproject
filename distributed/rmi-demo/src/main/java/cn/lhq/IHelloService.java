package cn.lhq;

import java.rmi.Remote;
import java.rmi.RemoteException;

//继承Remote
public interface IHelloService extends Remote {

    String sayHello(String msg) throws RemoteException;
}
