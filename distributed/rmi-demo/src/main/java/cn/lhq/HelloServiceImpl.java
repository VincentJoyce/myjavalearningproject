package cn.lhq;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//继承UnicastRemoteObject，并实现调用其构造方法
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "Hello " + msg;
    }
}
